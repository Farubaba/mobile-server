package com.farubaba.mobile.base.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GsonService implements JsonService<JsonElement> {

	public static final Gson gson = new GsonBuilder().create();
	private static GsonService instance = new GsonService();
	
	public static GsonService getInstance(){
		return instance;
	}
	
	@Override
	public <E> String toJsonString(E e) {
		return gson.toJson(e);
	}

	@Override
	public <T> T fromJson(String jsonString, Class<T> classOfT) {
		return gson.fromJson(jsonString, classOfT);
	}

	@Override
	public JsonElement parseJsonElement(String jsonString){
		if(jsonString != null){
			try{
				JsonElement jsonElement = new JsonParser().parse(jsonString);
				return jsonElement;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 判断给定的字符串是否是合法的JSON字符串
	 * @param jsonString
	 * @return
	 */
	@Override
	public boolean isValidJson(String jsonString){
		JsonElement jsonElement = parseJsonElement(jsonString);
		if(jsonElement != null){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isValidJsonObject(String jsonString){
		JsonElement jsonElement = parseJsonElement(jsonString);
		if(jsonElement != null && jsonElement.isJsonObject()){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isValidJsonArray(String jsonString){
		JsonElement jsonElement = parseJsonElement(jsonString);
		if(jsonElement != null && jsonElement.isJsonArray()){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isValidJsonNull(String jsonString){
		JsonElement jsonElement = parseJsonElement(jsonString);
		if(jsonElement != null && jsonElement.isJsonNull()){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isValidJsonPrimitive(String jsonString){
		JsonElement jsonElement = parseJsonElement(jsonString);
		if(jsonElement != null && jsonElement.isJsonPrimitive()){
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidJson(JsonElement jsonElement) {
		if(jsonElement != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidJsonObject(JsonElement jsonElement) {
		return jsonElement != null && jsonElement.isJsonObject();
	}

	@Override
	public boolean isValidJsonArray(JsonElement jsonElement) {
		return jsonElement != null && jsonElement.isJsonArray();
	}

	@Override
	public boolean isValidJsonNull(JsonElement jsonElement) {
		return jsonElement != null && jsonElement.isJsonNull();
	}

	@Override
	public boolean isValidJsonPrimitive(JsonElement jsonElement) {
		return jsonElement != null && jsonElement.isJsonPrimitive();
	}
	
	
}
