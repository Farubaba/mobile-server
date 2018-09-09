package com.farubaba.mobile.base.http.protocol;

public class HttpConfig {
	public static final int DEF_MAX_THREAD_PER_TASK = 3;
	public static final int DEF_THREAD_POOL_SIZE_FACTOR = 10;
	public static final int DEF_DOWNLOAD_CONNECT_TIMEOUT = 1000 * 10;
	public static final int DEF_DOWNLOAD_READ_TIMEOUT = 1000 * 10 * 3;
	public static final int DEF_HTTP_RETRY_COUNT = 6;
	public static final int DEF_DOWNLOAD_BUFFER_SIZE = 1024;

	private static int threadPoolSizeFactor = DEF_THREAD_POOL_SIZE_FACTOR;
	private static int maxThreadNumPerTask = DEF_MAX_THREAD_PER_TASK;
	private static int downloadConnectTimeout = DEF_DOWNLOAD_CONNECT_TIMEOUT;
	private static int downloadReadTimeout = DEF_DOWNLOAD_READ_TIMEOUT;
	private static int httpRetryCount = DEF_HTTP_RETRY_COUNT;
	private static int downloadBufferSize = DEF_DOWNLOAD_BUFFER_SIZE;

	public static int getThreadPoolSizeFactor() {
		return threadPoolSizeFactor > 0 ? threadPoolSizeFactor : DEF_THREAD_POOL_SIZE_FACTOR;
	}

	public static void setThreadPoolSizeFactor(int factor) {
		HttpConfig.threadPoolSizeFactor = factor > 0 ? factor : DEF_THREAD_POOL_SIZE_FACTOR;
	}

	public static int getMaxThreadNumPerTask() {
		return maxThreadNumPerTask > 0 ? maxThreadNumPerTask : DEF_MAX_THREAD_PER_TASK;
	}

	public static void setMaxThreadNumPerTask(int maxThreadNum) {
		HttpConfig.maxThreadNumPerTask = maxThreadNum > 0 ? maxThreadNum : DEF_MAX_THREAD_PER_TASK;
	}

	public static int getDownloadConnectTimeout() {
		return downloadConnectTimeout >= 0 ? downloadConnectTimeout : DEF_DOWNLOAD_CONNECT_TIMEOUT;
	}

	public static void setDownloadConnectTimeout(int downloadConnectTimeout) {
		HttpConfig.downloadConnectTimeout = downloadConnectTimeout >= 0 ? downloadConnectTimeout : DEF_DOWNLOAD_CONNECT_TIMEOUT;
	}

	public static int getDownloadReadTimeout() {
		return downloadReadTimeout >= 0 ? downloadReadTimeout : DEF_DOWNLOAD_READ_TIMEOUT;
	}

	public static void setDownloadReadTimeout(int downloadReadTimeout) {
		HttpConfig.downloadReadTimeout = downloadReadTimeout >= 0 ? downloadReadTimeout : DEF_DOWNLOAD_READ_TIMEOUT;
	}

	public static int getHttpRetryCount() {
		return httpRetryCount >= 0 ? httpRetryCount : DEF_HTTP_RETRY_COUNT;
	}

	public static void setHttpRetryCount(int httpRetryCount) {
		HttpConfig.httpRetryCount = httpRetryCount >= 0 ? httpRetryCount : DEF_HTTP_RETRY_COUNT;
	}

	public static int getDownloadBufferSize() {
		return downloadBufferSize >= 0 ? downloadBufferSize : DEF_DOWNLOAD_BUFFER_SIZE;
	}

	public static void setDownloadBufferSize(int downloadBufferSize) {
		HttpConfig.downloadBufferSize = downloadBufferSize > 0 ? downloadBufferSize : DEF_DOWNLOAD_BUFFER_SIZE;
	}

}
