package org.base.feature.http.impl;

import org.root.feature.interf.IModel;

/**
 * 定义常见的http请求，例如： get,post,返回json，java对象，集合等。
 * @author violet
 *
 */
public interface HttpAdapter {
	public <M extends IModel> RequestHandler sendDefaultRequest(RequestContext<M> requestContext);
	public <M extends IModel> RequestHandler sendTimeoutRequest(RequestContext<M> requestContext);
	public <M extends IModel> RequestHandler sendAuthenticatorRequest(RequestContext<M> requestContext);
	public <M extends IModel> RequestHandler sendInterceptorRequest(RequestContext<M> requestContext);
}
