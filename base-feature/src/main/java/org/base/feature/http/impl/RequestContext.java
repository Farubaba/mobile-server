package org.base.feature.http.impl;

import java.util.HashMap;
import java.util.Map;

import org.base.feature.http.body.Body;
import org.base.feature.http.model.ErrorResultType;
import org.root.feature.interf.IDataCallback;
import org.root.feature.interf.IModel;
import org.root.feature.interf.impl.ErrorResult;
import org.root.feature.utils.UrlUtil;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

/**
 * 
 * URL
 * method
 * header   key-value
 * headers  midiMap
 * parameter key-value
 * body     any string 
 * body 	stream
 * MultiPart
 * stream
 * callback
 * response code
 * response header
 * response body
 * 
 * @author violet
 *
 */
public class RequestContext<M extends IModel, RESULT> implements IRequestContext {
	public static final String CHARSET = "utf-8";
	public static final String PROTOCOL_CONTENT_TYPE_JSON = String.format("application/json; charset=%s", CHARSET);
    public static String PROTOCOL_CONTENT_TYPE_X_WWW_FORM_URLENCODED = String.format("application/x-www-form-urlencoded; charset=%s", CHARSET);
    /**
     * 需要将jsonString 转化的目标java类型
     */
    private Class<M> resultModelClass;
    private ErrorResultType errorResultType = ErrorResultType.ErrorResult;
    
	private int domainType;
    //scheme://host:port/
	private String domain;
	//user/api/v3?username=xxx#node
	//完整的URL = domain+pathQuery+key value parameters
	private String url;
	private HttpMethod method = HttpMethod.GET;
	/**
	 * request header
	 * 该集合一个key只能对应一个value
	 */
	private Map<String,String> header = new HashMap<String, String>();
	/**
	 * request headers
	 * 该集合一个key可以对应多个value
	 */
	private ListMultimap<String,String> headers = MultimapBuilder.hashKeys().arrayListValues().build();
	/**
	 * request query parameters
	 */
	private Map<String,String> querys = new HashMap<String, String>();
	//request body
	private Body requestBody;
	//MultiPart
	//stream
	//callback
	private RequestCallback<M> callback;
	//response code
	//response header
	//response body
	
	public String getDomain() {
		if(this.domain == null || !UrlUtil.isAbsolutedPath(this.domain)){
			this.domain = DomainFactory.getDomain(domainType);	
		}
		if(!this.domain.endsWith(UrlUtil.SEPERATOR_PATH)){
			this.domain = this.domain + UrlUtil.SEPERATOR_PATH;
		}
		if(this.domain == null){
			this.domain = UrlUtil.EMPTY_STRING;
		}
		return this.domain;
	}

	public RequestContext<M,RESULT> setDomain(String domain) {
		this.domain = domain;
		return this;
	}
	
	public String getUrl() {
		if(!UrlUtil.isAbsolutedPath(url)){
			//FIXME 拼接domain+path+query
			//第一种方式：动态循环拼接
			//第二种方式：String.format(url,String... param)
			this.url = getDomain() + UrlUtil.adjustUrl(url);
		}
		//Get请求参数append
		this.url = appendGetUrlParameters(url);
		return url;
	}
	
	
	/**
	 * Get请求参数append to url
	 * @param url
	 * @return
	 */
	private String appendGetUrlParameters(String url){
		if(HttpMethod.GET.equals(getMethod()) && getQuerys() != null && !getQuerys().isEmpty()){
			url = UrlUtil.appendUrlWithParams(url, getQuerys());
		}
		return url;
	}
	
	

	public RequestContext<M,RESULT> setUrl(String url) {
		this.url = url;
		return this;
	}
	public HttpMethod getMethod() {
		return method;
	}
	public RequestContext<M,RESULT> setMethod(HttpMethod method) {
		this.method = method;
		return this;
	}
	public Map<String, String> getHeader() {
		return header;
	}
	public RequestContext<M,RESULT> setHeader(Map<String, String> header) {
		this.header = header;
		return this;
	}
	public ListMultimap<String, String> getHeaders() {
		return headers;
	}
	public RequestContext<M,RESULT> setHeaders(ListMultimap<String, String> headers) {
		this.headers = headers;
		return this;
	}
	public Map<String, String> getQuerys() {
		return querys;
	}
	public RequestContext<M,RESULT> setQuerys(Map<String, String> querys) {
		this.querys = querys;
		return this;
	}
	
	public RequestContext<M,RESULT> setResultModelClass(Class<M> resultModelClass) {
		this.resultModelClass = resultModelClass;
		return this;
	}
	
	public Class<M> getResultModelClass() {
		return resultModelClass;
	}

	public int getDomainType() {
		return domainType;
	}

	public RequestContext<M,RESULT> setDomainType(int domainType) {
		this.domainType = domainType;
		return this;
	}

	public RequestCallback<M> getCallback() {
		return callback;
	}

	public RequestContext<M,RESULT> setCallback(RequestCallback<M> callback) {
		this.callback = callback;
		return this;
	}

	public ErrorResultType getErrorResultType() {
		return errorResultType;
	}

	public RequestContext<M,RESULT> setErrorResultType(ErrorResultType errorResultType) {
		this.errorResultType = errorResultType;
		return this;
	}

	public Body getRequestBody() {
		return requestBody;
	}

	public RequestContext<M,RESULT> setRequestBody(Body requestBody) {
		this.requestBody = requestBody;
		return this;
	}

}
