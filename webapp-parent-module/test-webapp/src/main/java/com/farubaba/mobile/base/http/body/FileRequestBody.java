package com.farubaba.mobile.base.http.body;

import java.io.File;

public class FileRequestBody extends Body{
	
	private String name;
	private String fileName;
	private File bodyContent;

	public FileRequestBody() {
		
	}
	
	public FileRequestBody(String mimeType, String name, String fileName, File bodyContent){
		this.setMimeType(mimeType);
		this.setName(name);
		this.setFileName(fileName);
		this.setBodyContent(bodyContent);
	}
	
	@Override
	public FileRequestBody setMimeType(String mimeType) {
		super.setMimeType(mimeType);
		return this;
	}
	
	public File getBodyContent() {
		return bodyContent;
	}

	public FileRequestBody setBodyContent(File bodyContent) {
		this.bodyContent = bodyContent;
		return this;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
