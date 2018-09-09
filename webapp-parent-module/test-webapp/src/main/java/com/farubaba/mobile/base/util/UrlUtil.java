package com.farubaba.mobile.base.util;

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
	public static final String PORT = "8090";
	public static final String HOST_SERVER = "http://localhost:"+PORT+"/";
	public static final String HTTP_URL_PREFIX = "http";
	public static final String HTTPS_URL_PREFIX = "https";
	public static final String WEBSOCKET_URL_PREFIX = "ws";
	public static final String WEBSOCKET_S_URL_PREFIX = "wss";
	public static final String EMPTY_STRING = "";
	public static final String NULL_STRING = "null";
	public static final String UNDEFINED = "undefined";
	public static final String NAME_VALUE_PAIR_SEPERATOR = "&";
	public static final String NAME_VALUE_SEPERATOR = "=";
	public static final String URL_PATH_PARAM_SEPERATOR = "?";
	public static final String PATH_SEPERATOR = "/";

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
			if(path.startsWith(HTTP_URL_PREFIX)){
				return true;
			}else if(path.startsWith(HTTPS_URL_PREFIX)){
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
		if(url.startsWith(UrlUtil.PATH_SEPERATOR)){
			url = url.substring(1, url.length());
		}
		return url;
	}

	/**
	 * 为Url添加参数 ?name1=value1&name2=value2
	 * @param url
	 * @param paramMap
	 * @return
	 */
	public static String appendUrlWithParams(String url, Map<String,String> paramMap){
		StringBuffer urlString = new StringBuffer();
		if(paramMap != null && !paramMap.isEmpty() && url != null){
			urlString.append(url);
			Set<String> keySet = paramMap.keySet();
			//保证在URL中已经包含 ?
			if(!url.contains(URL_PATH_PARAM_SEPERATOR)){
				if(url.endsWith(PATH_SEPERATOR)){
					int lastIndex = urlString.length() - 1;
					urlString.deleteCharAt(lastIndex);
				}
				urlString.append(URL_PATH_PARAM_SEPERATOR);
			}
			//url中包含了 ？ ，但 ？ 又不是最后一个字符，则说明url上已经有添加参数，后续只需要追加 &key=value
			url = urlString.toString();
			if(!url.endsWith(URL_PATH_PARAM_SEPERATOR) && !url.endsWith(NAME_VALUE_PAIR_SEPERATOR)){
				urlString.append(NAME_VALUE_PAIR_SEPERATOR);
			}
			//拼接key=value&key2=value2
			Iterator<String> iterator = keySet.iterator();
			int size = keySet.size();
	        for (int i=0; iterator.hasNext(); i++) {
	        	String key = iterator.next();
	            String value = paramMap.get(key);
	            urlString.append(key).append(NAME_VALUE_SEPERATOR).append(value);
	            //最后一个key=value后面不添加 &
	            if(i< size && i != size - 1){
	            	urlString.append(NAME_VALUE_PAIR_SEPERATOR);
	            }
	        }
		}
		return urlString.toString();
	}
	
}

