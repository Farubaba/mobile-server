package com.farubaba.mobile.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.base.feature.http.HttpManager;
import org.base.feature.http.body.StringRequestBody;
import org.base.feature.http.impl.HttpMethod;
import org.base.feature.http.impl.IModelResultCallback;
import org.base.feature.http.impl.MimeType;
import org.base.feature.http.impl.RequestContext;
import org.base.feature.http.impl.RequestHandler;
import org.base.feature.http.model.ObjectErrorModel;
import org.business.domain.dao.ListUser;
import org.business.domain.dao.ListUser2;
import org.http.feature.okhttp.OkHttpAdapter;
import org.junit.Test;
import org.root.feature.interf.impl.ErrorResult;
import org.root.feature.utils.ConcurrentUtil;


/**
 * Struts2-json-plugin 继承属性无法转化到JSON
 * MultipartBody 需要验证每一个part对应的MediaType是否正确设置
 * 
 * @author violet
 *
 */
public class OkHttpManagerDataCenter {
	
	/**
	 * 
	 * @author gaoge
	 * android 平台支持bks格式证书文件
	 * 生成bks文件命令：
	 * 
	 * keytool -importcert -v -trustcacerts -alias certfengjr -file server.crt 
	 * -keystore fengjr.bks -storetype BKS 
	 * -providerclass org.bouncycastle.jce.provider.BouncyCastleProvider -providerpath ./bcprov-jdk15on-146.jar 
	 * -storepass fengjr601
	 * 
	 * 其中server.crt是原格式证书
	 * fengjr.bks是目标生成证书
	 * fengjr601是bks格式文件证书密码
	 *
	 */
	public static final String port = "8443";
	public static final String API_CONTEXT= "http://127.0.0.1:"+port+"/test-webapp/";
	String domain = "http://127.0.0.1:"+port+"/test-webapp";
	String patternUrl = "api/user/v2?name=%1$s&name=%2$s";
	String fullUrl = "http://127.0.0.1:"+port+"/test-webapp/api/user/getUsersApi?name=%1$s&name=%2$s";
	
	@Test
	public void okHttpGetTest(){
	
		OkHttpAdapter manager = new OkHttpAdapter();
		Map<String,String> querys = new HashMap<String, String>();
		querys.put("name", "namevalue-from-map");
		querys.put("pwd", "123");
		RequestContext<ListUser> requestContext = new RequestContext<ListUser>()
				.setDomain(domain)
				.setUrl(String.format(patternUrl, "valueOfName1","valueOfName2"))
				.setQuerys(querys);
				
		manager.sendDefaultRequest(requestContext);
	}
	
	
	@Test
	public void okHttpGetFullUrlTest(){
	
		OkHttpAdapter manager = new OkHttpAdapter();
		Map<String,String> querys = new HashMap<String, String>();
		querys.put("name", "namevalue-from-map2");
		querys.put("pwd", "123456");
		
		RequestContext<ListUser> requestContext = new RequestContext<ListUser>();
		requestContext.setDomain(domain)
				.setUrl(String.format(fullUrl, "valueOfName11","valueOfName22"))
				.setQuerys(querys);
				
		manager.sendDefaultRequest(requestContext);
	}
	
	@Test
	public void asyncOkHttpGetTest(){
	
		System.out.println("发送请求的线程ID ："+Thread.currentThread().getId());
		
		HttpManager httpManager = HttpManager.getInstance();
		Map<String,String> querys = new HashMap<String, String>();
		querys.put("name", "namevalue-from-map2-asyc");
		querys.put("pwd", "123456");
		
		IModelResultCallback<ListUser2> callback = new IModelResultCallback<ListUser2>() {
			@Override
			public void onSuccess(ListUser2 result) {
					String name = result.getData().get(0).getUsername();
					System.out.println("接收response的线程ID："+Thread.currentThread().getId()+"  成功获得userName = "+name);
							
			}

			@Override
			public void onFailure(ErrorResult result) {
				// TODO Auto-generated method stub
				System.out.println("接收到的错误：  " + result.getMessage());
				
			}
		};
		RequestContext<ListUser2> requestContext = new RequestContext<ListUser2>()
				.setDomain(domain)
				.setUrl(String.format(fullUrl, "valueOfName11","valueOfName22"))
				.setQuerys(querys)
				.setCallback(callback)
				.setResultClass(ListUser2.class);
	
				
		httpManager.sendDefaultRequest(requestContext);
	}
	
	@Test
	public void asyncOkHttpGetTest2(){
	
		System.out.println("发送请求的线程ID ："+Thread.currentThread().getId());
		
		HttpManager httpManager = HttpManager.getInstance();
		
		Map<String,String> querys = new HashMap<String, String>();
		querys.put("name", "namevalue-from-map2-asyc-test2");
		querys.put("pwd", "1234567890");
		
		final CountDownLatch countDownLatch = ConcurrentUtil.newSingleStepCountDownLatch();
		IModelResultCallback<ListUser2> callback = new IModelResultCallback<ListUser2>() {
			@Override
			public void onSuccess(ListUser2 result) {
					String name = result.getData().get(0).getUsername();
					System.out.println("接收response的线程ID："+Thread.currentThread().getId()+"  成功获得userName = "+name);
					countDownLatch.countDown();	
			}

			@Override
			public void onFailure(ErrorResult result) {
				// TODO Auto-generated method stub
				System.out.println("接收到的错误：  " + result.getMessage());
				countDownLatch.countDown();	
				
			}
		};
		
		RequestContext<ListUser2> requestContext = new RequestContext<ListUser2>()
				//.setDomain(domain)
				.setUrl(String.format(fullUrl, "valueOfName11","valueOfName22"))
				.setQuerys(querys)
				.setCallback(callback)
				.setResultClass(ListUser2.class);
		
		RequestHandler handler = httpManager.sendDefaultRequest(requestContext);
		//you can cancel request like below:
		handler.cancelRequest();
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void httpManagerPostString(){
		final CountDownLatch countDownLatch = ConcurrentUtil.newSingleStepCountDownLatch();
		String postStringUrlWithUrlParamter = "http://127.0.0.1:"+port+"/test-webapp/api/bussiness/postString?name=%1$s&pwd=%2$s";
		postStringUrlWithUrlParamter = "https://127.0.0.1:"+port+"/test-webapp/api/bussiness/postString?name=%1$s&pwd=%2$s";
		String bodyContent = "hello body, this is the message post by a beautiful girl, who lives on the other size of the earth, don't you exciting?";
		HttpManager manager = HttpManager.getInstance();
		RequestContext<ObjectErrorModel> requestContext = new RequestContext<ObjectErrorModel>()
				.setUrl(String.format(postStringUrlWithUrlParamter, "lzg", "123"))
				.setMethod(HttpMethod.POST)
				.setRequestBody(new StringRequestBody()
						.setMimeType(MimeType.TEXT_X_MARKDOWN)
						.setBodyContent(bodyContent))
				.setResultClass(ObjectErrorModel.class)
				.setCallback(new IModelResultCallback<ObjectErrorModel>() {
					@Override
					public void onSuccess(ObjectErrorModel result) {
						System.out.println("正确返回 : result.display = " + result.getDisplay() );
						System.out.println("正确返回 : result.getError().display = " + result.getError().getDisplay() );
						countDownLatch.countDown();
					}

					@Override
					public void onFailure(ErrorResult result) {
						System.out.println("错误返回");
						countDownLatch.countDown();
					}
				});
		manager.sendDefaultRequest(requestContext);
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
