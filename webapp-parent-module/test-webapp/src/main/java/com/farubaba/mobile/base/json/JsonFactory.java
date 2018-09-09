package com.farubaba.mobile.base.json;
/**
 * 常用的Java JSON 解析库有：
 * <code>
 * google-gson
 * fastjson
 * Jackson
 * ...
 * </code>
 * 
 * 参考： http://www.json.org/
 * @author violet
 *
 */
public class JsonFactory {
	
	public static final int GSON = 1;
	public static final int FAST_JSON = 2;
	public static final int JACKSON = 3;
	public static final int MOSHI = 4;
	/**
	 * 默认采用GSON作为默认JSON解析器。其实，所有这些int参数都不应该写死在code里，
	 * 应该在配置文件中定义并获取
	 */
	public static final int DEFAULT = GSON;
	
	public static JsonService<?> getJsonService(){
		return getJsonService(DEFAULT);
	}
	
	/**
	 * 在应用中一般都只应该调用无参数的{@link #getJsonService()}}，
	 * 因为该方法在以后你替换JSON解析器时，无需做额外的修改，只需要修改{@link #DEFAULT}}即可。
	 * 除非你在应用中同时引入了两个或者更多的JSON解析器。 
	 * @param type
	 * @return
	 */
	public static JsonService<?> getJsonService(int type){
		switch(type){
		case GSON:
			return GsonService.getInstance();
		case FAST_JSON:
			//FIXME if you want replace GSON with FastJson, 
			//you are also required to create FastJsonService.java like GsonService.java
		case JACKSON:
			//FIXME if you want replace GSON with Jackson
			//you are also required to create JacksonService.java like GsonService.java
		case MOSHI:
			//FIXME if you want replace GSON with Jackson
			//you are also required to create JacksonService.java like GsonService.java
		default:
			return GsonService.getInstance();
		}
	}
}
