package com.farubaba.mobile.base.http.protocol;
/**
 * All the request methods that HTTP 1.1 supported. reference RFC2616 page 43
 */
public enum HttpMethod {
	GET, POST, HEAD, PUT, DELETE, PATCH, TRACE, CONNECT, OPTIONS,// Base RequestMethods
	MOVE, COPY, LINK, UNLINK, WRAPPED, EXTENSION_MOTHED// Base RequestMethods
}