package com.farubaba.mobile.base.http.protocol;
/**
 * https://blog.csdn.net/lipeigang1109/article/details/59057525
 *
 * https://blog.csdn.net/selinda001/article/details/79338766
 */
public enum RequestHeader {
    /**
     * 指定客户端能够接收的内容类型
     * text/html, *\/*; q=0.01
     */
	ACCEPT("Accept"),
    /**
     * 浏览器可以接受的字符编码集
     * Accept-Charset: iso-8859-5
     */
	ACCEPT_CHARSET("Accept-Charset"),
    /**
     * 指定浏览器可以支持的web服务器返回内容压缩编码类型
     * Accept-Encoding: compress, gzip
     */
	ACCEPT_ENCODING("Accept-Encoding"),
    /**
     * 浏览器可接受的语言
     * Accept-Language: en,zh
     */
	ACCEPT_LANGUAGE("Accept-Language"),
    /**
     * HTTP授权的授权证书
     * Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
     */
	AUTHORIZATION("Authorization"),
    /**
     * 可以请求网页实体的一个或者多个子范围字段
     * Accept-Ranges: bytes
     */
	ACCEPT_RANGES("Accept-Ranges"),
    /**
     * 表示是否需要持久连接。（HTTP 1.1默认进行持久连接）
     * Connection: keep-alive
     */
	CONNECTION("Connection"),
    /**
     * 指定请求和响应遵循的缓存机制
     * Cache-Control: no-cache
     */
	CACHE_CONTROL("Cache-Control"),
    /**
     * 请求的与实体对应的MIME信息
     * Content-Type: application/x-www-form-urlencoded
     */
	CONTENT_TYPE("Content-Type"),
    /**
     * 请求的内容长度
     * Content-Length: 348
     */
	CONTENT_LENGTH("Content-Length"),
    /**
     * 只请求实体的一部分，指定范围
     * bytes=0-499
     */
	RANGE("Range"),
    /**
     * HTTP请求发送时，会把保存在该请求域名下的所有cookie值一起发送给web服务器。
     * BAIDUID=DCA9E59A959EEF41FC1D9397123FC18E:FG=1; H_PS_PSSID=8347_5228_1423_7800_8235_8677_8489_8056_8559_6506_8503_6018_8592_8626_8579_7799_8447_8737_8435
     */
	COOKIE("Cookie"),
    /**
     * 指定请求的服务器的域名和端口号
     * suggestion.baidu.com
     */
	HOST("Host"),
    /**
     * 请求发送的日期和时间
     * Date: Tue, 15 Nov 2010 08:12:31 GMT
     */
    DATE("Date"),
    /**
     * 请求的特定的服务器行为
     * Expect: 100-continue
     */
    EXPECT("Expect"),
    /**
     * 发出请求的用户的Email
     * From: user@email.com
     */
    FROM("From"),
    /**
     * 只有请求内容与实体相匹配才有效
     * If-Match: “737060cd8c284d8af7ad3082f209582d”
     */
    IF_MATCH("If-Match"),
    /**
     * 如果请求的部分在指定时间之后被修改则请求成功，未被修改则返回304代码
     * If-Modified-Since: Sat, 29 Oct 2010 19:43:31 GMT
     */
    IF_MODIFIED_SINCE("If-Modified-Since"),
    /**
     * 如果内容未改变返回304代码，参数为服务器先前发送的Etag，与服务器回应的Etag比较判断是否改变
     * If-None-Match: “737060cd8c284d8af7ad3082f209582d”
     */
    IF_NONE_MATCH("If-None-Match"),
    /**
     * 如果实体未改变，服务器发送客户端丢失的部分，否则发送整个实体。参数也为Etag
     * If-Range: “737060cd8c284d8af7ad3082f209582d”
     */
    IF_RANGE("If-Range"),
    /**
     * 先前网页的地址，当前请求网页紧随其后,即来路
     * http://www.baidu.com/s?ie=UTF-8&wd=RandomAccessFile
     */
	REFERER("Referer"),
    /**
     * 只在实体在指定时间之后未被修改才请求成功
     * If-Unmodified-Since: Sat, 29 Oct 2010 19:43:31 GMT
     */
    IF_UNMODIFIED_SINCE("If-Unmodified-Since"),
    /**
     * 限制信息通过代理和网关传送的时间
     * Max-Forwards: 10
     */
    MAX_FORWARDS("Max-Forwards"),
    /**
     * 用来包含实现特定的指令
     * Pragma: no-cache
     */
    PRAGMA("Pragma"),
    /**
     * 连接到代理的授权证书
     * Proxy-Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
     */
    PROXY_AUTHORIZATION("Proxy-Authorization"),
    /**
     * 客户端愿意接受的传输编码，并通知服务器接受接受尾加头信息
     * TE: trailers,deflate;q=0.5
     */
    TE("TE"),
    /**
     * 向服务器指定某种传输协议以便服务器进行转换（如果支持）
     * Upgrade: HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11
     */
    UPGRADE("Upgrade"),
    /**
     * 通知中间网关或代理服务器地址，通信协议
     * Via: 1.0 fred, 1.1 nowhere.com (Apache/1.1)
     */
    VIA("Via"),
    /**
     * 关于消息实体的警告信息
     * Warn: 199 Miscellaneous warning
     */
    WARNING("Warning"),
    /**
     * User-Agent的内容包含发出请求的用户信息
     * Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36
     */
	USER_AGENT("User-Agent");

	private String headerName;

	private RequestHeader(String headerName) {
		this.headerName = headerName;
	}

	public String getValue() {
		return this.headerName;
	}
}
