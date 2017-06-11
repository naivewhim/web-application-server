package model;

import java.util.Map;

import constants.HttpMethod;
import util.HttpRequestUtil;

public class RequestLine {
	private HttpMethod httpMethod;
	private String requestPath;
	private String version;

	private Map<String, String> params;

	public RequestLine(String requestLine) {
		String[] requestComponents = requestLine.split(" ");

		this.httpMethod = HttpMethod.valueOf(requestComponents[0]);

		String requestUrl = requestComponents[1];
		int index = requestUrl.indexOf("?");
		if (index != -1) {
			this.requestPath = requestUrl.substring(0, index);
			this.params = HttpRequestUtil.parseQueryString(requestUrl.substring(index + 1));
		} else {
			this.requestPath = requestUrl;
		}

		this.version = requestComponents[2];
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public String getVersion() {
		return version;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
