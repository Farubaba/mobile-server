package com.farubaba.mobile.base.util;


import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 提供安全关闭各种数据源的功能
 *
 * @author violet
 * @modify
 * @see
 */
public final class CloseUtil {

	private static final String TAG = "StreamUtil";

	public static void closeIO(Closeable closeable){
		if(closeable != null){
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeIOQuietly(Closeable closeable){
		if(closeable != null){
			try {
				closeable.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}

	public static void closeIO(final Closeable... closeables) {
		if (closeables == null) return;
		for (Closeable closeable : closeables) {
			closeIO(closeable);
		}
	}

	public static void closeIOQuietly(final Closeable... closeables) {
		if (closeables == null) return;
		for (Closeable closeable : closeables) {
			closeIOQuietly(closeable);
		}
	}


	public static void closeHttpURLConnection(HttpURLConnection conn) {
		if (conn != null) {
			try {
				conn.disconnect();
				//LogManager.getInstance().dt(TAG, "HttpURLConnection disconnect");
			}
			catch (Exception e) {
				//LogManager.getInstance().e(TAG, "randomAHttpURLConnectionccessFile close exception : " + e.getMessage());
			}
		}
	}

}
