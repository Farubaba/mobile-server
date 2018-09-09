package com.farubaba.mobile.base.http.protocol;

/**
 * https://msdn.microsoft.com/zh-cn/library/windows/apps/system.net.httpresponseheader(d=printer,v=vs.100).aspx/html
 * 
 * @author violet
 *
 */
public enum ResponseHeader {
	/**
	 * 指定请求/响应链上所有缓存机制必须服从的缓存指令。
	 */
	CACHE_CONTROL("Cache-Control"),
	/**
	 * 指定特定连接需要的选项。
	 */
	CONNECTION("Connection"),
	/**
	 * 指定响应产生的日期和时间。
	 */
	DATE("Date"),
	/**
	 * 指定用于维护持久连接的参数。
	 */
	KEEPALIVE("KeepAlive"),
	/**
	 * 指定可应用于请求/响应链上的任何代理的特定于实现的指令。
	 */
	PRAGMA("Pragma"),
	/**
	 * 指定指示的标头字段在消息（使用分块传输编码方法进行编码）的尾部显示。
	 */
	TRAILER("Trailer"),
	/**
	 * 指定对消息正文应用哪种类型的转换（如果有）。
	 */
	TRANSFER_ENCODING("Transfer-Encoding"),
	/**
	 * 指定客户端支持的附加通信协议。
	 */
	UPGRADE("Upgrade"),
	/**
	 * 指定网关和代理程序要使用的中间协议。
	 */
	VIA("Via"),
	
	/**
	 * 指定关于可能未在消息中反映的消息的状态或转换的附加信息。
	 */
	WARNING("Warning"),
	/**
	 * 指定支持的 HTTP 方法集
	 */
	ALLOW("Allow"),
	/**
	 * 指定伴随正文数据的长度（以字节为单位）。
	 */
	CONTENT_LENGTH("Content-Length"),
	
	/**
	 * 指定伴随正文数据的 MIME 类型。
	 */
	CONTENT_TYPE("Content-Type"),
	
	/**
	 * 指定已应用于伴随正文数据的编码。
	 */
	CONTENT_ENCODING("Content-Encoding"),
	
	/**
	 * 指定自然语言或伴随正文数据的语言
	 */
	CONTENT_LANGAUGE("Content-Langauge"),
	/**
	 * 指定可以从中获取伴随正文的 URI。
	 */
	CONTENT_LOCATION("Content-Location"),
	/**
	 * 指定伴随正文数据的 MD5 摘要，用于提供端到端消息完整性检查。
	 */
	CONTENT_MD5("Content-MD5"),
	/**
	 * 指定客户端请求返回的响应的单个或多个子范围来代替整个响应。
	 */
	RANGE("Range"),
	/**
	 * 指定日期和时间，在此之后伴随的正文数据应视为陈旧的。
	 */
	EXPIRES("Expires"),
	/**
	 * 指定上次修改伴随的正文数据的日期和时间。
	 */
	LAST_MODIFIED("Last-Modified"),
	/**
	 * 指定服务器接受的范围。
	 */
	ACCEPT_RANGES("Accept-Ranges"),
	/**
	 * 指定自起始服务器生成响应以来的时间长度（以秒为单位）。
	 */
	AGE("Age"),
	/**
	 * 指定请求的变量的当前值
	 */
	ETAG("Etag"),
	/**
	 * 指定为获取请求的资源而将客户端重定向到的 URI。
	 */
	LOCATION("Location"),
	/**
	 * 指定客户端必须对代理验证其自身。
	 */
	PROXY_AUTHENTICATE("Proxy-Authenticate"),
	/**
	 * 指定某个时间（以秒为单位）或日期和时间，在此时间之后客户端可以重试其请求。
	 */
	RETRY_AFTER("Retry-After"),
	/**
	 * 指定关于起始服务器代理的信息。
	 */
	SERVER("Server"),
	/**
	 * 指定提供给客户端的 Cookie 数据。
	 */
	SET_COOKIE("Set-Cookie"),
	/**
	 * 指定用于确定缓存的响应是否为新响应的请求标头。
	 */
	VARY("Vary"),
	/**
	 * 指定客户端必须对服务器验证其自身。
	 */
	WWW_AUTHENTICATE("WWW-Authenticate");
	
	private String headerName;

	private ResponseHeader(String headerName) {
		this.headerName = headerName;
	}

	public String getValue() {
		return this.headerName;
	}
}
