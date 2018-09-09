package com.farubaba.mobile.base.http.body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MultipartBody;

/**
 * MultipartBody有以下几种组成形式：
 * 
 * builder.addPart(body)
 * builder.addPart(headers, body)
 * builder.addFormDataPart(name, value)
 * builder.addFormDataPart(name, filename, body)
 * 
 * builder.addPart(part)
 *
 * Part part = Part.create(body)
 * Part part = Part.create(headers, body)
 * Part part = Part.createFormData(name, value)
 * Part part = Part.createFormData(name, filename, body)
 * 
 * 综上，我们只需要提供处理以下几种情况即可：
 * 1）RequestBody
 * 2）Header & RequestBody
 * 3）Key=value
 * 4）key-fileName-RequestBody
 * 5）Part --其实的对上面4种的封装
 * 
 * @param content
 * @return
 */
public class MultipartRequestBody extends Body {
	
	private List<Body> bodyList;
	private List<HeaderedBody> headersBodyList;
	private Map<String, String> formKeyValues;
	private List<FileRequestBody> fileBodyList;
	
	@Override
	public MultipartRequestBody setMimeType(String mimeType) {
		super.setMimeType(mimeType);
		return this;
	}
	
	public List<Body> getBodyList() {
		return bodyList == null ? new LinkedList<Body>() : bodyList ;
	}
	public void setBodyList(List<Body> bodyList) {
		this.bodyList = bodyList;
	}
	public List<HeaderedBody> getHeadersBodyList() {
		return headersBodyList == null ? new LinkedList<HeaderedBody>() : headersBodyList;
	}
	public void setHeadersBodyList(List<HeaderedBody> headersBodyList) {
		this.headersBodyList = headersBodyList;
	}
	public Map<String, String> getFormKeyValues() {
		return formKeyValues == null ? new HashMap<String, String>() : formKeyValues;
	}
	public void setFormKeyValues(Map<String, String> formKeyValues) {
		this.formKeyValues = formKeyValues;
	}
	public List<FileRequestBody> getFileBodyList() {
		return fileBodyList == null ? new ArrayList<FileRequestBody>() : fileBodyList;
	}
	public void setFileBodyList(List<FileRequestBody> fileBodyList) {
		this.fileBodyList = fileBodyList;
	}
	
	
	
}
