/**
 * Copyright 2017 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Eunji, Lim
 */
public class HttpResponse {
	private static final Logger log = LoggerFactory.getLogger(HttpResponse.class);

	public static final String WEB_DIRECTORY = "./webapp";

	private DataOutputStream dos;

	private Map<String, String> header = new HashMap<String, String>();

	public HttpResponse(OutputStream out) {
		this.dos = new DataOutputStream(out);
	}

	public void addHeader(String key, String value) {
		header.put(key, value);
	}

	public void forward(String url) {

		if (url.endsWith(".css")) {
			header.put("Content-Type", "text/css");
		} else if (url.endsWith(".js")) {
			header.put("Content-Type", "application/javascript");
		} else if (url.endsWith(".html")) {
			header.put("Content-Type", "text/html;charset=utf-8");
		}

		File view = new File(WEB_DIRECTORY + url);

		try {
			byte[] body = Files.readAllBytes(view.toPath());
			header.put("Content-Length", String.valueOf(body.length));

			response200Header(body.length);
			responseBody(body);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	public void forwardBody(String body) {
		byte[] contents = body.getBytes();
		
		header.put("Content-Type", "text/html;charset=utf-8");
		header.put("Content-Length", String.valueOf(contents.length));
		
		response200Header(contents.length);
		responseBody(contents);
	}

	public void sendRedirect(String redirectUrl) {
		try {
			dos.writeBytes("HTTP/1.1 302 Redirect \r\n");
			processHeader();
			dos.writeBytes("Location: " + redirectUrl + " \r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	private void response200Header(int lengthOfBodyContent) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			processHeader();
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void responseBody(byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.writeBytes("\r\n");
			dos.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	private void processHeader() {
		try {
			Set<String> keys = header.keySet();
			for(String key : keys) {
				dos.writeBytes(key + ": " + header.get(key) + " \r\n");
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
