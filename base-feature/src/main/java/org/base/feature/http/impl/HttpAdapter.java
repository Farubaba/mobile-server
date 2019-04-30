package org.base.feature.http.impl;

import org.root.feature.interf.IModel;

/**
 * 定义常见的http请求，例如： get,post,返回json，java对象，集合等。
 * @author violet
 *
 */
public interface HttpAdapter {
	public <M extends IModel,RESULT> RequestHandler sendDefaultRequest(RequestContext<M,RESULT> requestContext);
	public <M extends IModel,RESULT> RequestHandler sendTimeoutRequest(RequestContext<M,RESULT> requestContext);
	public <M extends IModel,RESULT> RequestHandler sendAuthenticatorRequest(RequestContext<M,RESULT> requestContext);
	public <M extends IModel,RESULT> RequestHandler sendInterceptorRequest(RequestContext<M,RESULT> requestContext);
}
