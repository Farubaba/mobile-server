package com.farubaba.mobile.base.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.farubaba.mobile.base.http.protocol.HttpConfig;
import com.farubaba.mobile.base.http.protocol.HttpMethod;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpUtils {
	private static final String TAG = "HttpUtils";
	public static final int UNKNOWN_CONTENT_LENGTH = -1;
	public static final String FORMAT_HEADER_VALUE_RANGE = "bytes=%s-%s";
	public static final String FORMAT_HEADER_VALUE_RANGE_START = "bytes=%s-";
	public static final int MAX_REDIRECTS = 6;
	public static final String URL_SEPERATOR = "/";
	public static final String RANDOM_ACCESSFILE_OPEN_MODE = "rwd";
	public static final int STREAM_READ_END = -1;

	public static HttpUtils newInstance() {
		return new HttpUtils();
	}

	/**
	 * 如果Url字符串格式正确，则返回URL对象，否则返回null
	 * 
	 * @param url
	 * @return <b>URL</b>
	 * @author violet
	 * @throws
	 * @see
	 * @modify
	 */
	public URL buildURL(String url) {
		try {
			return new URL(url);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param directory
	 * @param conn
	 * @return <b>String</b>
	 * @author violet
	 * @throws
	 * @see
	 * @modify
	 */
	public String getFilePathFromConnection(File directory, HttpURLConnection conn) {
		if (directory != null && !directory.exists()) {
			boolean dirExists = directory.mkdirs();
		}
		return directory.getAbsolutePath() + parseFileNameFromConnection(conn);
	}

	/**
	 * 从Redirect之后的最终连接对象connection中解析出经过UrlEncode的文件名。<br>
	 * 为了能实现这个功能，需要在建立连接之前设置Connection跟随Redirect跳转：<br>
	 * <br>
	 * <code>conn.setInstanceFollowRedirects(true);</code><br>
	 * <br>
	 * 
	 * 这样，在我们收到200 或者 206 响应码时，就可以从connection中过去到最终Redirect到的真实URL，<br>
	 * 该URL已经被URLEncoder加密过，需要使用URLDecoder来解密。<br>
	 * <br>
	 * <code>
	 * URL finalUrl = conn.getURL();<br>
	 * String decodeUrl = URLDecoder.decode(encodedUrl, "UTF-8");</code><br>
	 * <br>
	 * 
	 * @param conn
	 * @return <b>String</b>
	 * @author violet
	 * @throws
	 * @see
	 * @modify
	 */
	public String parseFileNameFromConnection(HttpURLConnection conn) {
		String encodedUrl = conn.getURL().toString();
		String fileName = UUID.randomUUID().toString();
		try {
			String decodeUrl = URLDecoder.decode(encodedUrl, "UTF-8");
			if (decodeUrl != null) {
				fileName = decodeUrl.substring(decodeUrl.lastIndexOf(URL_SEPERATOR));
			}
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * 根据传入的开始值和结束值，构建RequestHeader：Range的Value值。
	 * 
	 * @param start
	 * @param end
	 * @return <b>String</b>
	 * @author violet
	 * @throws
	 * @see
	 * @modify
	 */
	public String buildRangeHeaderValue(int start, int end) {
		if (end == 0 || end == UNKNOWN_CONTENT_LENGTH || end < start) {
			return String.format(FORMAT_HEADER_VALUE_RANGE_START, start);
		}
		else {
			return String.format(FORMAT_HEADER_VALUE_RANGE, start, end);
		}
	}

	/**
	 * 探测要下载的文件的ContentLength，如果返回-1 则表示，文件长度未知，不能支持多线程断点下载，需要使用一个线程来下载所有数据。
	 * 
	 * @param url
	 * @return <b>int</b>
	 * @author violet
	 * @see
	 * @modify
	 */
	public int getContentLength(URL url) {
		HttpURLConnection conn = null;
		try {
			if (url != null) {
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod(HttpMethod.GET.toString());// default is GET
				conn.setDoInput(true);// default is true
				conn.setDoOutput(false);// default is false
				conn.setConnectTimeout(HttpConfig.getDownloadConnectTimeout());// default 0 never timeout
				conn.setReadTimeout(HttpConfig.getDownloadReadTimeout());// default 0 never timeou
				int responseCode = conn.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					int contentLength = conn.getContentLength();
					// FIXME use logger to replace me
					System.out.println(("getContentLength = " + contentLength));
					return contentLength;
				}
			}
		}
		catch (ProtocolException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			CloseUtil.closeHttpURLConnection(conn);
		}
		return UNKNOWN_CONTENT_LENGTH;
	}

	public List<DownloadBlock> getDownloadBlocks(int contentLength, int threadCount) {
		if (threadCount <= 0) {
			threadCount = HttpConfig.getMaxThreadNumPerTask();
		}
		List<DownloadBlock> blocks = new ArrayList<DownloadBlock>();
		if (contentLength == 0 || contentLength == UNKNOWN_CONTENT_LENGTH) {
			DownloadBlock block = new DownloadBlock();
			block.setStart(0);
			block.setEnd(UNKNOWN_CONTENT_LENGTH);
			block.setSize(UNKNOWN_CONTENT_LENGTH);
			blocks.add(block);
		}
		else {
			int blockSize = contentLength / threadCount;
			int start = 0;
			int end = contentLength;
			for (int i = 0; i < threadCount; i++) {
				// 最后一个下载块，需要直接下载到contentLength位置
				if (i == threadCount - 1) {
					start = i * blockSize;
					end = contentLength;
					blockSize = end - start;
				}
				else {
					start = i * blockSize;
					end = (i + 1) * blockSize - 1;
				}
				DownloadBlock block = new DownloadBlock();
				block.setStart(start);
				block.setEnd(end);
				block.setSize(blockSize);
				blocks.add(block);
			}
		}
		return blocks;
	}

	public static class DownloadBlock {

		private int start;
		private int end;
		private int size;

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

	}
}
