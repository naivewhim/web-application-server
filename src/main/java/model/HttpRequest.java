/**
 * Copyright 2017 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.HttpMethod;
import util.HttpRequestUtil;
import util.IOUtils;

/**
 * @author Eunji, Lim
 */
public class HttpRequest {
	private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);

	private RequestLine requestLine;
	private Map<String, String> header;
	private Map<String, String> params;

	public HttpRequest(InputStream in) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

			requestLine = new RequestLine(br.readLine());

			Map<String, String> headerMap = new HashMap<>();
			String headerStr;
			while (!"".equals(headerStr = br.readLine())) {
				StringTokenizer headerTokenizer = new StringTokenizer(headerStr, ":");
				headerMap.put(headerTokenizer.nextToken(), headerTokenizer.nextToken());
			}
			this.header = headerMap;

			if (requestLine.getHttpMethod().isPost()) {
				// multipart/form-data or application/x-www-form-urlencoded
				int contentLength = Integer.parseInt(this.getHeader().get("Content-Length").trim());
				if (contentLength > 0) {
					this.params = HttpRequestUtil.parseQueryString(IOUtils.readData(br, contentLength));
				}
			} else {
				this.params = this.requestLine.getParams();
			}
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public HttpMethod getHttpMethod() {
		return requestLine.getHttpMethod();
	}

	public String getRequestPath() {
		return requestLine.getRequestPath();
	}

	public String getVersion() {
		return requestLine.getVersion();
	}
}
