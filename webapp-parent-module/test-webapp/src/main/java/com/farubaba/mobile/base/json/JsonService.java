package com.farubaba.mobile.base.json;

public interface JsonService<JSON_ELEMENT>{
	
	public <T> T fromJson(String jsonString, Class<T> classOfT);
	
	public <E> String toJsonString(E e);
	
	public JSON_ELEMENT parseJsonElement(String jsonString);
	public boolean isValidJson(String jsonString);
	public boolean isValidJsonObject(String jsonString);
	public boolean isValidJsonArray(String jsonString);
	public boolean isValidJsonNull(String jsonString);
	public boolean isValidJsonPrimitive(String jsonString);

	public boolean isValidJson(JSON_ELEMENT jsonElement);
	public boolean isValidJsonObject(JSON_ELEMENT jsonElement);
	public boolean isValidJsonArray(JSON_ELEMENT jsonElement);
	public boolean isValidJsonNull(JSON_ELEMENT jsonElement);
	public boolean isValidJsonPrimitive(JSON_ELEMENT jsonElement);
}
