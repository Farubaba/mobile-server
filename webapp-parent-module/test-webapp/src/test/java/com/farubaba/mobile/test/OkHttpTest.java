package com.farubaba.mobile.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.farubaba.mobile.demo.OkHttpFunBase;
import com.farubaba.mobile.server.model.User;

import okhttp3.MediaType;

public class OkHttpTest {

	OkHttpFunBase okHttpBase;
	@Before
	public void setUp(){
		okHttpBase = new OkHttpFunBase();
		System.setProperty("http.proxySet", "true"); 
		System.setProperty("http.proxyHost", "127.0.0.1"); 
		System.setProperty("http.proxyPort", "8888");
	}
	
	@Test
	public void testSynchronousGet(){
		List<User> users = okHttpBase.synchronousGetListUser();
		assertNotNull(users);
		assertTrue(users.size() > 0);
		assertNotNull(users.get(0));
		assertEquals("farubaba", users.get(0).getUsername());
		assertEquals("叶小钗", users.get(1).getUsername());
	}
	
	@Test
	public void asynchronousGetUserList(){
		List<User> users = okHttpBase.asynchronousGet();
		assertNotNull(users);
		assertTrue(users.size() > 0);
		assertNotNull(users.get(0));
		assertEquals("farubaba", users.get(0).getUsername());
		assertEquals("叶小钗", users.get(1).getUsername());
	}
	
	@Test
	public void accessHeader(){
		String cacheHeader = okHttpBase.accessingHeaders();
		assertNotNull(cacheHeader);
		assertEquals("server cache control", cacheHeader);
		
	}
	
	@Test
	public void postString(){
		String post = "this string is post from client, will return from server";
		MediaType contentType = MediaType.parse("text/plain; charset=utf-8");
		String returnString = okHttpBase.postPlainText(contentType,post);
		assertNotNull(returnString);
		assertEquals(post, returnString);
	}
	
	@Test
	public void postStream(){
		String post = "this will be convert to stream";
		MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
		String returnString = okHttpBase.postStreamAndReturn(mediaType, post);
		assertNotNull(returnString);
		System.out.println("returnString = "+returnString);
		System.out.println("post = "+ post);
		assertEquals(post, returnString);
	}
	
	@Test
	public void postFile(){
		//File file = new File("/environment/git-repo/test-webapp/src/main/webapp/resources/files/test.md");
		File file = new File("/environment/git-repo/mobile-server/webapp-parent-module/test-webapp/src/main/webapp/resources/files/test.md");
		MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
		String returnFileContent = okHttpBase.postFile(mediaType,file);
		System.out.println("客户端上传文件之后从response中获取的内容：");
		System.out.println(returnFileContent);
		assertNotNull(returnFileContent);
		assertTrue(returnFileContent.contains("测试文件上传") || returnFileContent.contains("html"));
	}
	
	@Test
	public void postForm(){
		String value = "testname";
		String returnJsonString = okHttpBase.postingFormParameters("name",value);
		assertTrue(returnJsonString.contains(value));
	}
	
	@Test 
	public void postMultiPart(){
		String result = okHttpBase.postMultiPart("key","value", "postbodystring");
	}
}
