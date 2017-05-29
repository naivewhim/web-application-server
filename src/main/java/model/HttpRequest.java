/**
 * Copyright 2017 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package model;

import java.util.Map;

/**
 * @author Eunji, Lim
 */
public class HttpRequest {
	private String httpMethod;
	private String requestUrl;
	private Map<String, String> params;

	public HttpRequest(String httpMethod, String requestUrl, Map<String, String> params) {
		this.httpMethod = httpMethod;
		this.requestUrl = requestUrl;
		this.params = params;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
