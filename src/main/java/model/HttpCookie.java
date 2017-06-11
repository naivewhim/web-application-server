package model;

import java.util.Map;

import util.HttpRequestUtil;

public class HttpCookie {
	private Map<String, String> cookieMap;
	
	HttpCookie(String cookieValue) {
		cookieMap = HttpRequestUtil.parseCookies(cookieValue);
	}
	
	public String getCookie(String name) {
		return cookieMap.get(name);
	}
}
