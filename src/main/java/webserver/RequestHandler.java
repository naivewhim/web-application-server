package webserver;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.HttpRequest;
import model.HttpResponse;
import model.User;
import util.HttpRequestUtils;
import util.HttpResponseUtil;

public class RequestHandler extends Thread {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
	public static final String DEFAULT_URL = "/index.html";
	public static final String WEB_DIRECTORY = "./webapp";

	private Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	public void run() {
		log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
			connection.getPort());

		try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
			service(in, out);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return;
	}

	private void service(InputStream in, OutputStream out) throws IOException {
		HttpRequest httpRequest = HttpRequestUtils.parseHttpRequest(in);
		HttpResponse httpResponse = HttpResponseUtil.parseHttpResponse(out);

		if (httpRequest.getRequestUrl().equals("/user/create")) {
			Map<String, String> params = httpRequest.getParams();
			// TODO : util
			User user = new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
			DataBase.addUser(user);
			log.debug("[Success addUser] :: userId : {}", user.getUserId());
		}

		goView(httpResponse, httpRequest);
	}

	private void goView(HttpResponse httpResponse, HttpRequest httpRequest) throws IOException {
		DataOutputStream dos = httpResponse.getDos();
		File view = new File(WEB_DIRECTORY + httpRequest.getRequestUrl());

		if (!view.isFile()) {
			view = new File(WEB_DIRECTORY + DEFAULT_URL);
			byte[] body = Files.readAllBytes(view.toPath());
			response302Header(dos, DEFAULT_URL);
			responseBody(dos, body);
		}

		byte[] body = Files.readAllBytes(view.toPath());
		response200Header(dos, body.length);
		responseBody(dos, body);
	}

	private void response302Header(DataOutputStream dos, String location) {
		try {
			dos.writeBytes("HTTP/1.1 302 Found \r\n");
			dos.writeBytes("Location: " + location + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
			dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void responseBody(DataOutputStream dos, byte[] body) {
		try {
			dos.write(body, 0, body.length);
			dos.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
