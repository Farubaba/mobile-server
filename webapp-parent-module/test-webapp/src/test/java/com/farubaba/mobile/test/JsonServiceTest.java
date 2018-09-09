package com.farubaba.mobile.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.farubaba.mobile.base.json.JsonFactory;
import com.farubaba.mobile.base.json.JsonService;
import com.farubaba.mobile.server.dao.DataCenter;
import com.farubaba.mobile.server.dao.DataCenterImpl;
import com.farubaba.mobile.server.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonServiceTest {

	private Gson gson = new GsonBuilder().create();
	@Before
	public void setUp(){
		
	}
	
    private static final Class<?>[] PRIMITIVE_TYPES = { int.class, long.class, short.class,
	      float.class, double.class, byte.class, boolean.class, char.class, Integer.class, Long.class,
	      Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class };
	@Test
	public void testJsonService(){
		DataCenter dataCenter = new DataCenterImpl();
		JsonService<?> jsonService = JsonFactory.getJsonService();
		
		//合法的JSON
		List<Book> bookList = dataCenter.getBookList("v587");
		Map<String,Book> bookMap = dataCenter.getBookMap("v123");
		Book book = dataCenter.getBook("123", "book name", "book type", "v404");
		String[] strs = new String[]{"{}","[]","{  }","[  ]",""," ","null","NULL","Null","NuLl"};
		int a = 20;
		boolean b = true;
		char c = 10;
		char cc = 'a';
		Double d = Double.valueOf(20.90);
		
		//不合法的JSON
		String jsonNull = null;
		String plainString = "this is primitive json";
		assertFalse(jsonService.isValidJson(jsonNull));
		assertFalse(jsonService.isValidJsonObject(jsonNull));
		assertFalse(jsonService.isValidJsonArray(jsonNull));
		assertFalse(jsonService.isValidJsonNull(jsonNull));
		assertFalse(jsonService.isValidJsonPrimitive(jsonNull));
	
		assertFalse(jsonService.isValidJson(plainString));
		assertFalse(jsonService.isValidJsonObject(plainString));
		assertFalse(jsonService.isValidJsonArray(plainString));
		assertFalse(jsonService.isValidJsonNull(plainString));
		assertFalse(jsonService.isValidJsonPrimitive(plainString));
		
		
		assertTrue(jsonService.isValidJson(gson.toJson(bookList)));
		assertFalse(jsonService.isValidJsonObject(gson.toJson(bookList)));
		assertTrue(jsonService.isValidJsonArray(gson.toJson(bookList)));
		assertFalse(jsonService.isValidJsonNull(gson.toJson(bookList)));
		assertFalse(jsonService.isValidJsonPrimitive(gson.toJson(bookList)));
		
		assertTrue(jsonService.isValidJson(gson.toJson(bookMap)));
		assertTrue(jsonService.isValidJsonObject(gson.toJson(bookMap)));
		assertFalse(jsonService.isValidJsonArray(gson.toJson(bookMap)));
		assertFalse(jsonService.isValidJsonNull(gson.toJson(bookMap)));
		assertFalse(jsonService.isValidJsonPrimitive(gson.toJson(bookMap)));
		
		assertTrue(jsonService.isValidJson(gson.toJson(book)));
		assertTrue(jsonService.isValidJsonObject(gson.toJson(book)));
		assertFalse(jsonService.isValidJsonArray(gson.toJson(book)));
		assertFalse(jsonService.isValidJsonNull(gson.toJson(book)));
		assertFalse(jsonService.isValidJsonPrimitive(gson.toJson(book)));
		
		for(String s : strs){
			if(s.equals(strs[0])){//"{}"
				assertTrue(jsonService.isValidJson(s));
				assertTrue(jsonService.isValidJsonObject(s));
				assertFalse(jsonService.isValidJsonArray(s));
				assertFalse(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[1])){//"[]"
				assertTrue(jsonService.isValidJson(s));
				assertFalse(jsonService.isValidJsonObject(s));
				assertTrue(jsonService.isValidJsonArray(s));
				assertFalse(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[2])){//"{ }"
				assertTrue(jsonService.isValidJson(s));
				assertTrue(jsonService.isValidJsonObject(s));
				assertFalse(jsonService.isValidJsonArray(s));
				assertFalse(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[3])){//"[ ]"
				assertTrue(jsonService.isValidJson(s));
				assertFalse(jsonService.isValidJsonObject(s));
				assertTrue(jsonService.isValidJsonArray(s));
				assertFalse(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[4])){//""
				assertTrue(jsonService.isValidJson(s));
				assertFalse(jsonService.isValidJsonObject(s));
				assertFalse(jsonService.isValidJsonArray(s));
				//""-->JsonNull
				assertTrue(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[5])){//" "
				assertTrue(jsonService.isValidJson(s));
				assertFalse(jsonService.isValidJsonObject(s));
				assertFalse(jsonService.isValidJsonArray(s));
				//" "-->JsonNull
				assertTrue(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[6])){//"null"
				assertTrue(jsonService.isValidJson(s));
				assertFalse(jsonService.isValidJsonObject(s));
				assertFalse(jsonService.isValidJsonArray(s));
				//null-->JsonNull
				assertTrue(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[7])){//"NULL"
				assertTrue(jsonService.isValidJson(s));
				assertFalse(jsonService.isValidJsonObject(s));
				assertFalse(jsonService.isValidJsonArray(s));
				//NULL-->JsonNull
				assertTrue(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[8])){//"Null"
				assertTrue(jsonService.isValidJson(s));
				assertFalse(jsonService.isValidJsonObject(s));
				assertFalse(jsonService.isValidJsonArray(s));
				//Null-->JsonNull
				assertTrue(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}else if(s.equals(strs[9])){//"NuLl"
				assertTrue(jsonService.isValidJson(s));
				assertFalse(jsonService.isValidJsonObject(s));
				assertFalse(jsonService.isValidJsonArray(s));
				//NuLl-->JsonNull
				assertTrue(jsonService.isValidJsonNull(s));
				assertFalse(jsonService.isValidJsonPrimitive(s));
			}
		}
		
		assertTrue(jsonService.isValidJson(String.valueOf(a)));
		assertFalse(jsonService.isValidJsonObject(String.valueOf(a)));
		assertFalse(jsonService.isValidJsonArray(String.valueOf(a)));
		assertFalse(jsonService.isValidJsonNull(String.valueOf(a)));
		assertTrue(jsonService.isValidJsonPrimitive(String.valueOf(a)));
		
		assertTrue(jsonService.isValidJson(String.valueOf(b)));
		assertFalse(jsonService.isValidJsonObject(String.valueOf(b)));
		assertFalse(jsonService.isValidJsonArray(String.valueOf(b)));
		assertFalse(jsonService.isValidJsonNull(String.valueOf(b)));
		assertTrue(jsonService.isValidJsonPrimitive(String.valueOf(b)));
		
		assertTrue(jsonService.isValidJson(String.valueOf(c)));
		assertFalse(jsonService.isValidJsonObject(String.valueOf(c)));
		assertFalse(jsonService.isValidJsonArray(String.valueOf(c)));
		System.out.println("空白 c = " + String.valueOf(c));
		//assertFalse(jsonService.isValidJsonNull(String.valueOf(c)));
		assertTrue(jsonService.isValidJsonNull(String.valueOf(c)));
		assertFalse(jsonService.isValidJsonPrimitive(String.valueOf(c)));
		
		assertTrue(jsonService.isValidJson(String.valueOf(cc)));
		assertFalse(jsonService.isValidJsonObject(String.valueOf(cc)));
		assertFalse(jsonService.isValidJsonArray(String.valueOf(cc)));
		System.out.println("空白 cc = " + String.valueOf(cc));
		//assertFalse(jsonService.isValidJsonNull(String.valueOf(cc)));
		assertFalse(jsonService.isValidJsonNull(String.valueOf(cc)));
		assertTrue(jsonService.isValidJsonPrimitive(String.valueOf(cc)));
		
		assertTrue(jsonService.isValidJson(String.valueOf(d)));
		assertFalse(jsonService.isValidJsonObject(String.valueOf(d)));
		assertFalse(jsonService.isValidJsonArray(String.valueOf(d)));
		assertFalse(jsonService.isValidJsonNull(String.valueOf(d)));
		assertTrue(jsonService.isValidJsonPrimitive(String.valueOf(d)));
	}
}
