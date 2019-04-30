package org.root.feature.json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

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
	public <A> List<A> fromJsonToList(String jsonString, Class<A> classOfA) {
		if(getJsonType(jsonString).isArray()) {
			return gson.fromJson(jsonString, new TypeToken<List<A>>(){}.getType());
		}
		return null;
	}

	@Override
	public <B> void handleJson(String jsonString, Class<B> clazz) {
		// TODO Auto-generated method stub
		
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

	@Override
	public JsonType getJsonType(String jsonString) {
		if(jsonString != null) {
			System.out.println(jsonString);
		}
		JsonElement jsonElement = parseJsonElement(jsonString);
		JsonType jsonType = new JsonType();
		if(jsonElement == null) {
			jsonType.setValid(false);
		}
		if(jsonElement != null) {
			if(jsonElement.isJsonArray()) {
				jsonType.setArray(true);
			}else if(jsonElement.isJsonObject()) {
				jsonType.setObject(true);
			}else if(jsonElement.isJsonPrimitive()) {
				jsonType.setPrimitive(true);
			}else if(jsonElement.isJsonNull()) {
				jsonType.setJsonNull(true);
			}
		}
		return jsonType;
	}

	@Override
	public boolean isValidJson(JsonType jsonType) {
		if(jsonType == null) {
			return false;
		}else {
			return jsonType.isValid();
		}
	}

	@Override
	public boolean isValidJsonObject(JsonType jsonType) {
		if(jsonType == null) {
			return false;
		}else {
			return jsonType.isObject();
		}
	}

	@Override
	public boolean isValidJsonArray(JsonType jsonType) {
		if(jsonType == null) {
			return false;
		}else {
			return jsonType.isArray();
		}
	}

	@Override
	public boolean isValidJsonNull(JsonType jsonType) {
		if(jsonType == null) {
			return false;
		}else {
			return jsonType.isJsonNull();
		}
	}

	@Override
	public boolean isValidJsonPrimitive(JsonType jsonType) {
		if(jsonType == null) {
			return false;
		}else {
			return jsonType.isPrimitive();
		}
	}

}
