package org.root.feature.utils;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * foo://example.com:8042/over/there?name=ferret#nose
 * 
 * foo://     example.com:8042   /over/there ?name=ferret  #nose
 * scheme     authority                path      query     fragment
 * 
 * @author violet
 *
 */
public class UrlUtil {
	public static final String TAG = UrlUtil.class.getSimpleName();
	public static final String SCHEME_HTTP = "http";
	public static final String SCHEME_HTTPS = "https";
	public static final String SCHEME_WEBSOCKET = "ws";
	public static final String SCHEME_WEBSOCKET_S = "wss";
	public static final String SEPERATOR_SCHECME_HOST_PROTOCOL = "://";
	public static final String SEPERATOR_HOST_PORT = ":";
	public static final String SEPERATOR_PATH = "/";
	public static final String SEPERATOR_URL_PATH_PARAM = "?";
	public static final String SEPERATOR_NAME_VALUE_PAIR = "&";
	public static final String SEPERATOR_NAME_VALUE = "=";
	public static final String SEPERATOR_FRAGMENT = "#";
	
	public static final String EMPTY_STRING = "";
	public static final String NULL_STRING = "null";
	public static final String UNDEFINED = "undefined";
	

	public static UrlUtil newInstance(){
		return new UrlUtil();
	}

	public static String check(String value) {
		if (value == null
				|| NULL_STRING.equals(value.trim())
				|| NULL_STRING.equalsIgnoreCase(value)
				|| UNDEFINED.equalsIgnoreCase(value)) {
			return EMPTY_STRING;
		}
		return value;
	}
	
	public static boolean isAbsolutedPath(String path){
		if(path != null){
			if(path.startsWith(SCHEME_HTTP)){
				return true;
			}else if(path.startsWith(SCHEME_HTTPS)){
				return true;
			}
		}else{
			//LogHelper.e(TAG, "Url is null. please check url before use.");
		}
		return false;
	}
	
	/**
	 * 使用参数填充Url占位符
	 * @param url
	 * @param params
	 * @return
	 */
	public static String formatUrlWithParams(String url, Object... params){
		if(params != null && url != null){
			return String.format(Locale.CHINA, url, params);
		}
		return url;
	}
	
	/**
	 * 调整URL “/”
	 * @param url
	 * @return
	 */
	public static String adjustUrl(String url){
		if(url == null){
			url = UrlUtil.EMPTY_STRING;
		}
		if(url.startsWith(UrlUtil.SEPERATOR_PATH)){
			url = url.substring(1, url.length());
		}
		return url;
	}

	/**
	 * 为Url添加参数 ?name1=value1&name2=value2
	 * @param fullUrl
	 * @param paramMap
	 * @return
	 */
	public static String appendUrlWithParams(String fullUrl, Map<String,String> paramMap){
		StringBuffer urlString = new StringBuffer();
		if(paramMap != null && !paramMap.isEmpty() && fullUrl != null){
			urlString.append(fullUrl);
			Set<String> keySet = paramMap.keySet();
			//保证在URL中已经包含 ?
			if(!fullUrl.contains(SEPERATOR_URL_PATH_PARAM)){
				if(fullUrl.endsWith(SEPERATOR_PATH)){
					int lastIndex = urlString.length() - 1;
					urlString.deleteCharAt(lastIndex);
				}
				urlString.append(SEPERATOR_URL_PATH_PARAM);
			}
			//url中包含了 ？ ，但 ？ 又不是最后一个字符，则说明url上已经有添加参数，后续只需要追加 &key=value
			fullUrl = urlString.toString();
			if(!fullUrl.endsWith(SEPERATOR_URL_PATH_PARAM) && !fullUrl.endsWith(SEPERATOR_NAME_VALUE_PAIR)){
				urlString.append(SEPERATOR_NAME_VALUE_PAIR);
			}
			//拼接key=value&key2=value2
			Iterator<String> iterator = keySet.iterator();
			int size = keySet.size();
	        for (int i=0; iterator.hasNext(); i++) {
	        	String key = iterator.next();
	            String value = paramMap.get(key);
	            urlString.append(key).append(SEPERATOR_NAME_VALUE).append(value);
	            //最后一个key=value后面不添加 &
	            if(i< size && i != size - 1){
	            	urlString.append(SEPERATOR_NAME_VALUE_PAIR);
	            }
	        }
		}
		return urlString.toString();
	}
	
	public static String appendUrlWithParams(String scheme, String host, int port, String path, Map<String,String> paramMap) {
		return appendUrlWithParams(buildUrl(scheme, host, port, path), paramMap);
	}
	
	/**
	 * 拼接Url = scheme://host:port/path?key=value#note
	 * 
	 * @param scheme
	 * @param host
	 * @param port
	 * @param path
	 * @return
	 */
	public static String buildUrl(String scheme, String host, int port, String path) {
		StringBuilder sb = new StringBuilder();
		if(scheme != null) {
			sb.append(scheme).append(SEPERATOR_SCHECME_HOST_PROTOCOL);
		}
		if(host != null) {
			if(port > 0) {
				if(host.endsWith(SEPERATOR_PATH)) {
					sb.append(host.substring(0, host.length()-1));
				}else {
					sb.append(host);
				}
				sb.append(SEPERATOR_HOST_PORT).append(port).append(SEPERATOR_PATH);
			}else {
				sb.append(host);
				if(!host.endsWith(SEPERATOR_PATH)) {
					sb.append(SEPERATOR_PATH);
				}	
			}
		}
		if(path != null) {
			if(path.startsWith(SEPERATOR_PATH)) {
				sb.append(path.substring(1));
			}else {
				sb.append(path);
			}
			if(!path.endsWith(SEPERATOR_PATH)) {
				sb.append(SEPERATOR_PATH);
			}
		}
		return sb.toString();
	}
	
	
}

