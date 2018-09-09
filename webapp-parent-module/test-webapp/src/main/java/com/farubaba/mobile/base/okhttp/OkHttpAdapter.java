package com.farubaba.mobile.base.okhttp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import com.farubaba.mobile.base.http.body.Body;
import com.farubaba.mobile.base.http.body.FileRequestBody;
import com.farubaba.mobile.base.http.body.FormRequestBody;
import com.farubaba.mobile.base.http.body.HeaderedBody;
import com.farubaba.mobile.base.http.body.MultipartRequestBody;
import com.farubaba.mobile.base.http.body.StreamRequestBody;
import com.farubaba.mobile.base.http.body.StringRequestBody;
import com.farubaba.mobile.base.http.model.IModel;
import com.farubaba.mobile.base.http.protocol.HttpAdapter;
import com.farubaba.mobile.base.http.protocol.HttpMethod;
import com.farubaba.mobile.base.http.protocol.IModelResultCallback;
import com.farubaba.mobile.base.http.protocol.RequestContext;
import com.farubaba.mobile.base.http.protocol.RequestHandler;
import com.farubaba.mobile.base.json.JsonFactory;
import com.farubaba.mobile.base.json.JsonService;
import com.farubaba.mobile.base.util.CloseUtil;
import com.farubaba.mobile.base.util.IOUtil;
import com.farubaba.mobile.server.model.ErrorResult;
import com.google.common.collect.ListMultimap;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.Dns;
import okhttp3.EventListener;
import okhttp3.EventListener.Factory;
import okhttp3.FormBody;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.BufferedSink;

/**
 * http默认实现是使用okhttp，如果需要更换实现，这仿造该类建立新的实现，设置给HttpManager即可。
 * 易拔插，易替换。
 * 
 * @author violet
 *
 */
public class OkHttpAdapter implements HttpAdapter{
	
	private OkHttpClient okHttpClient;
	private JsonService<?> jsonService = JsonFactory.getJsonService();
	
	public OkHttpAdapter(){
		okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(20, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.build();
	}
	
	public OkHttpClient.Builder newBuilder(OkHttpAdapter okHttpAdapter){
		return okHttpClient.newBuilder();
	}

	public OkHttpClient build(){
		File cacheFileDir = new File("cache");
		okHttpClient.newBuilder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.writeTimeout(10, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.pingInterval(3 * 1000l, TimeUnit.MILLISECONDS)
				.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
				.cache(new Cache(cacheFileDir, 10 * 1024 * 1024)) //10M
				
				.connectionPool(new ConnectionPool())
				.addInterceptor(new Interceptor(){
					@Override
					public Response intercept(Chain chain) throws IOException {
						return chain.proceed(chain.request());
					}
					
				})
				.addNetworkInterceptor(new Interceptor(){
					@Override
					public Response intercept(Chain chain) throws IOException {
						return null;
					}
					
				})
				//.connectionSpecs(List<ConnectionSpec>)
				//.authenticator(new AuthorizeCallback(authnID, authzID))
				.certificatePinner(new CertificatePinner.Builder().build())
				.cookieJar(new CookieJar(){

					@Override
					public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
						
					}

					@Override
					public List<Cookie> loadForRequest(HttpUrl url) {
						return null;
					}
					
				})
				.dispatcher(new Dispatcher())
				.dns(new Dns() {
					
					@Override
					public List<InetAddress> lookup(String hostname) throws UnknownHostException {
						return null;
					}
				})
				.eventListener(new EventListener(){

					@Override
					public void callStart(Call call) {
						System.out.println("eventListener callStart");
						super.callStart(call);
					}

					@Override
					public void dnsStart(Call call, String domainName) {
						System.out.println("eventListener dnsStart");
						super.dnsStart(call, domainName);
					}

					@Override
					public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
						System.out.println("eventListener dnsEnd");
						super.dnsEnd(call, domainName, inetAddressList);
					}

					@Override
					public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
						System.out.println("eventListener connectStart");
						super.connectStart(call, inetSocketAddress, proxy);
					}

					@Override
					public void secureConnectStart(Call call) {
						System.out.println("eventListener secureConnectStart");
						super.secureConnectStart(call);
					}

					@Override
					public void secureConnectEnd(Call call, Handshake handshake) {
						System.out.println("eventListener secureConnectEnd");
						super.secureConnectEnd(call, handshake);
					}

					@Override
					public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy,
							Protocol protocol) {
						System.out.println("eventListener connectEnd");
						super.connectEnd(call, inetSocketAddress, proxy, protocol);
					}

					@Override
					public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy,
							Protocol protocol, IOException ioe) {
						System.out.println("eventListener connectionAcquired");
						super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
					}

					@Override
					public void connectionAcquired(Call call, Connection connection) {
						System.out.println("eventListener connectionAcquired");
						super.connectionAcquired(call, connection);
					}

					@Override
					public void connectionReleased(Call call, Connection connection) {
						System.out.println("eventListener connectionReleased");
						super.connectionReleased(call, connection);
					}

					@Override
					public void requestHeadersStart(Call call) {
						System.out.println("eventListener requestHeadersStart");
						super.requestHeadersStart(call);
					}

					@Override
					public void requestHeadersEnd(Call call, Request request) {
						System.out.println("eventListener requestHeadersEnd");
						super.requestHeadersEnd(call, request);
					}

					@Override
					public void requestBodyStart(Call call) {
						System.out.println("eventListener requestBodyStart");
						super.requestBodyStart(call);
					}

					@Override
					public void requestBodyEnd(Call call, long byteCount) {
						System.out.println("eventListener requestBodyEnd");
						super.requestBodyEnd(call, byteCount);
					}

					@Override
					public void responseHeadersStart(Call call) {
						System.out.println("eventListener responseHeadersStart");
						super.responseHeadersStart(call);
					}

					@Override
					public void responseHeadersEnd(Call call, Response response) {
						System.out.println("eventListener responseHeadersEnd");
						super.responseHeadersEnd(call, response);
					}

					@Override
					public void responseBodyStart(Call call) {
						System.out.println("eventListener responseBodyStart");
						super.responseBodyStart(call);
					}

					@Override
					public void responseBodyEnd(Call call, long byteCount) {
						System.out.println("eventListener responseBodyEnd");
						super.responseBodyEnd(call, byteCount);
					}

					@Override
					public void callEnd(Call call) {
						System.out.println("eventListener Call call");
						super.callEnd(call);
					}

					@Override
					public void callFailed(Call call, IOException ioe) {
						System.out.println("eventListener callFailed");
						super.callFailed(call, ioe);
					}
					
				})
				.eventListenerFactory(new Factory() {
					@Override
					public EventListener create(Call call) {
						return null;
					}
				})
				.followRedirects(false)
				.followSslRedirects(false)
				.hostnameVerifier(OkHostnameVerifier.INSTANCE)
				//.authenticator(authenticator)
				//.protocols(List<Protocol>)
				.proxyAuthenticator(new Authenticator() {
					
					@Override
					public Request authenticate(Route route, Response response) throws IOException {
						return null;
					}
				})
				.proxySelector(new ProxySelector() {
					
					@Override
					public List<Proxy> select(URI uri) {
						return null;
					}
					
					@Override
					public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
						
					}
				})
				.retryOnConnectionFailure(true)
				.socketFactory(SocketFactory.getDefault())
				.sslSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault(), new X509TrustManager(){

					@Override
					public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
						
					}

					@Override
					public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
						
					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					
				})
				.build();
		return okHttpClient;
	}
	
	public OkHttpClient getOkHttpClient() {
		return okHttpClient;
	}

	public OkHttpAdapter setOkHttpClient(OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
		return this;
	}
	
	private <M extends IModel> Request.Builder prepareRequestBuilder(RequestContext<M> requestContext){
		Request.Builder requestBuilder = new Request.Builder();
		setHeader(requestContext, requestBuilder);
		addHeaders(requestContext, requestBuilder);
		requestBuilder.url(requestContext.getUrl());
		RequestBody body = prepareRequestBodyIfNeed(requestContext, requestBuilder);
		HttpMethod method = requestContext.getMethod();
		switch(method){
			case GET: //完全方法
				return requestBuilder.get();
			case HEAD: //安全方法
				return requestBuilder.head();
			case POST:
				return requestBuilder.post(body);
			case PUT:
				return requestBuilder.put(body);
			case DELETE:
				return requestBuilder.delete(body);
			case PATCH:
				return requestBuilder.patch(body);
			case CONNECT:
			case TRACE:
			case OPTIONS:
			default:
				return requestBuilder.get();
			
		}
	}
	
	private <M extends IModel> RequestBody prepareRequestBodyIfNeed(RequestContext<M> requestContext, Builder requestBuilder) {
		Body body = requestContext.getRequestBody();
		RequestBody requestBody = convertToRequestBody(body);
		return requestBody;
	}
	
	/**
	 * 这里提供了目前支持的RequestBody类型，如果需要添加其他类型，扩展这里即可。
	 * 
	 * @param body
	 * @return
	 */
	private RequestBody convertToRequestBody(Body body){
		RequestBody requestBody = null;
		if(body != null){
			if(body instanceof StringRequestBody){
				StringRequestBody content = (StringRequestBody)body;
				requestBody = prepareStringBody(content);
			}else if(body instanceof StreamRequestBody){
				StreamRequestBody content = (StreamRequestBody)body;
				requestBody = prepareStreamBody(content);
			}else if(body instanceof FileRequestBody){
				FileRequestBody content = (FileRequestBody) body;
				requestBody = prepareFileBody(content);
			}else if(body instanceof FormRequestBody){
				FormRequestBody content = (FormRequestBody) body;
				requestBody = prepareFormBody(content);
			}else if(body instanceof MultipartRequestBody){
				MultipartRequestBody content =  (MultipartRequestBody) body;
				requestBody = prepareMultipartBody(content);
			}
		}
		return requestBody;
	}

	private RequestBody prepareMultipartBody(MultipartRequestBody content) {
		RequestBody body = null;
		MultipartBody.Builder builder = new MultipartBody.Builder();
		//FIXME 多个不同的part应该使用哪一个mimeType
		//builder.setType(type);
		if(content != null){
			List<Body> bodyList = content.getBodyList();
			List<FileRequestBody> fileBodyList = content.getFileBodyList();
			Map<String,String> formKeyValues = content.getFormKeyValues();
			List<HeaderedBody> headersBodyList = content.getHeadersBodyList();
			addFormDataKeyValueParts(builder,formKeyValues);
			addFormDataFileParts(builder, fileBodyList);
			addBodyParts(builder, bodyList);
			addHeadersBodyParts(builder, headersBodyList);
			body = builder.build();
		}
		return body;
	}

	private void addHeadersBodyParts(okhttp3.MultipartBody.Builder builder, List<HeaderedBody> headersBodyList) {
		for(HeaderedBody item : headersBodyList){
			Headers headers = Headers.of(item.getHeaders());
			Body body = item.getBodyContent();
			builder.addPart(headers, convertToRequestBody(body));
		}
	}

	private void addBodyParts(okhttp3.MultipartBody.Builder builder, List<Body> bodyList) {
		for(Body body : bodyList){
			builder.addPart(convertToRequestBody(body));
		}
	}

	private void addFormDataFileParts(okhttp3.MultipartBody.Builder builder, List<FileRequestBody> fileBodyList) {
		for(FileRequestBody item : fileBodyList){
			builder.addFormDataPart(item.getName(), item.getFileName(), convertToRequestBody(item));
		}
	}

	private void addFormDataKeyValueParts(okhttp3.MultipartBody.Builder builder, Map<String, String> formKeyValues) {
		Set<String> keySet = formKeyValues.keySet();
		for(String key : keySet){
			builder.addFormDataPart(key, formKeyValues.get(key));
		}
	}

	private RequestBody prepareFormBody(FormRequestBody content) {
		RequestBody body = null;
		if(content != null){
			Map<String, String> formMap = content.getBodyContent();
			if(formMap != null && !formMap.isEmpty()){
				FormBody.Builder builder = new FormBody.Builder();
				for(String key : formMap.keySet()){
					if(content.isEncoded()){
						builder.addEncoded(key, formMap.get(key));
					}else{
						builder.add(key, formMap.get(key));
					}
				}
				body = builder.build();
			}
		}
		return body;
	}

	private RequestBody prepareFileBody(FileRequestBody content) {
		RequestBody body = null;
		if(content != null){
			body = RequestBody.create(MediaType.parse(content.getMimeType()), content.getBodyContent());
		}
		return body;
	}

	private RequestBody prepareStreamBody(StreamRequestBody content) {
		RequestBody body = null;
		if(content != null){
			body = new RequestBody() {
				@Override
				public void writeTo(BufferedSink sink) throws IOException {
					InputStream in = content.getBodyContent();
					byte[] buffer = new byte[16 * 1024];
					if(in != null){
						int len = 0;
						while(IOUtil.hasMoreContent(len = in.read(buffer))){
							sink.write(buffer, 0, len);
						}	
					}
					CloseUtil.closeIO(in);
				}
				
				@Override
				public MediaType contentType() {
					return MediaType.parse(content.getMimeType());
				}
			};
		}
		return body;
	}

	private RequestBody prepareStringBody(StringRequestBody content) {
		if(content != null){
			return RequestBody.create(MediaType.parse(content.getMimeType()), content.getBodyContent());
		}
		return null;
	}

	/**
	 * 添加header，多次添加同一个key不会覆盖,同一个key可以对应多个值
	 * @param requestContext
	 * @param requestBuilder
	 */
	private <M extends IModel> void addHeaders(RequestContext<M> requestContext, Request.Builder requestBuilder) {
		ListMultimap<String, String> multiHeaders = requestContext.getHeaders();
		Set<String> multiHeaderKeys = multiHeaders.keySet();
		for(String key : multiHeaderKeys){
			List<String> headerValueList = multiHeaders.get(key);
			for(String value : headerValueList){
				requestBuilder.addHeader(key, value);
			}
		}
	}

	/**
	 * 添加header，多次添加同一个key会覆盖
	 * 
	 * @param requestContext
	 * @param builder
	 * @return
	 */
	private <M extends IModel> Request.Builder setHeader(RequestContext<M> requestContext,Request.Builder builder){
		Map<String,String> header = requestContext.getHeader();
		Set<String> headerKeys = header.keySet();
		for(String key : headerKeys){
			builder.header(key, header.get(key));
		}
		return builder;
	}

	@Override
	public <M extends IModel> RequestHandler sendDefaultRequest(RequestContext<M> requestContext) {
		Request request = prepareRequestBuilder(requestContext).build();
		//okHttpClient.newBuilder().build().newCall(request);
		Call call = getOkHttpClient().newCall(request);
		enqueueRequest(call, requestContext);
		RequestHandler requestHandler = new RequestHandler() {
			@Override
			public void cancelRequest() {
				call.cancel();
			}
		};
		return requestHandler;
	}

	private <M extends IModel> void enqueueRequest(Call call, RequestContext<M> requestContext) {
		call.enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if(response.isSuccessful()){
					try{
						String jsonString = response.body().string();
						System.out.println("jsonString = "+ jsonString);
						IModelResultCallback<M> callback = requestContext.getCallback();
						if(callback != null){
							callback.onSuccess(jsonService.fromJson(jsonString, requestContext.getResultClass()));	
						}
					}catch (Exception e) {
						e.printStackTrace();
						//FIXME 不同的Exception可以用来区分不用的错误逻辑，不如：认证失败，协议错误，等
						IOException ioe = new IOException(e);
						onFailure(call, ioe);
					}
				}
			}
			
			@Override
			public void onFailure(Call call, IOException e) {
				//FIXME 这里可以根据实际中需要，不断完善错误类型。
				e.printStackTrace();
				IModelResultCallback<M> callback = requestContext.getCallback();
				ErrorResult errorResult = new ErrorResult(-1, "根据实际需要，扩充错误类型,例如：json语法错误，authority错误，SSL证书错误等等", "其他错误，不特殊处理");
				if(callback != null){
					if(call.isCanceled()){
						errorResult.setCode(ErrorResult.CODE_REQUEST_CANCEL)
						.setMessage("客户端取消了request")
						.setDisplay("无特殊处理");
					}
					callback.onFailure(errorResult);	
				}
			}
			
		});		
	}

	@Override
	public <M extends IModel> RequestHandler sendTimeoutRequest(RequestContext<M> requestContext) {
		Request request = prepareRequestBuilder(requestContext).build();
		//配置单个Request
		OkHttpClient client = getOkHttpClient().newBuilder()
				.connectTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(20, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.build(); 
		Dispatcher dispather = client.dispatcher();
		dispather.setMaxRequests(100);// default is 64
		dispather.setMaxRequestsPerHost(8);// default is 5
		
		Call call = client.newCall(request);

		enqueueRequest(call, requestContext);
		RequestHandler requestHandler = new RequestHandler() {
			@Override
			public void cancelRequest() {
				call.cancel();
			}
		};
		return requestHandler;
	}
	
	@Override
	public <M extends IModel> RequestHandler sendAuthenticatorRequest(RequestContext<M> requestContext) {
		Request request = prepareRequestBuilder(requestContext).build();
		//配置单个Request
		OkHttpClient client = getOkHttpClient().newBuilder()
				.connectTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(20, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.authenticator(new Authenticator() {
					@Override
					public Request authenticate(Route route, Response response) throws IOException {
						//我们已经尝试过认证，但是失败了，说明提供的认证是错误的，所以不必再尝试，直接返回null，结束。
						if (response.request().header("Authorization") != null) {
				             return null; // Give up, we've already attempted to authenticate.
				        }
						
						System.out.println("Authenticating for response: " + response);
			            System.out.println("Challenges: " + response.challenges());
			            String credential = Credentials.basic("farubaba", "123456");
			            //说明我们使用当前认证失败，不必再尝试，直接返回null，结束。
			            if (credential.equals(response.request().header("Authorization"))) {
			                return null; // If we already failed with these credentials, don't retry.
			            }
			            
			            //如果尝试大于3次，则结束尝试
			            if (responseCount(response) >= 3) {
			                return null; // If we've failed 3 times, give up.
			              }
			            
			            return response.request().newBuilder()
			                .header("Authorization", credential)
			                .build();
					}
					
					private int responseCount(Response response) {
					    int result = 1;
					    while ((response = response.priorResponse()) != null) {
					      result++;
					    }
					    return result;
					}
				})
				.build(); 
		Dispatcher dispather = client.dispatcher();
		dispather.setMaxRequests(100);// default is 64
		dispather.setMaxRequestsPerHost(8);// default is 5
		
		Call call = client.newCall(request);

		enqueueRequest(call, requestContext);
		RequestHandler requestHandler = new RequestHandler() {
			@Override
			public void cancelRequest() {
				call.cancel();
			}
		};
		return requestHandler;
	}

	@Override
	public <M extends IModel> RequestHandler sendInterceptorRequest(RequestContext<M> requestContext) {
		Request request = prepareRequestBuilder(requestContext).build();
		//配置单个Request
		OkHttpClient client = getOkHttpClient().newBuilder()
				.connectTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(20, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.addInterceptor(new Interceptor(){

					@Override
					public Response intercept(Chain chain) throws IOException {
						return null;
					}})
				.build(); 
		Dispatcher dispather = client.dispatcher();
		dispather.setMaxRequests(100);// default is 64
		dispather.setMaxRequestsPerHost(8);// default is 5
		
		Call call = client.newCall(request);

		enqueueRequest(call, requestContext);
		RequestHandler requestHandler = new RequestHandler() {
			@Override
			public void cancelRequest() {
				call.cancel();
			}
		};
		return requestHandler;
	}
}
