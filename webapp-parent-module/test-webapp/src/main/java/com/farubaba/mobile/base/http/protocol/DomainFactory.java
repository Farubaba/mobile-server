package com.farubaba.mobile.base.http.protocol;

public class DomainFactory {
	public static String getDomain(int type){
		switch(type){
			case 1:
				return "http://localhost:8080/";
			default:
				return "http://localhost:8080/";	
		}
	}
}
