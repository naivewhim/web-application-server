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
import model.Cookie;
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

		String requestUrl = httpRequest.getRequestUrl();
		if (requestUrl.equals("/index.html") && httpRequest.getHttpMethod().equals("GET")) {
			httpResponse.setLocation("/index.html");

			goView(httpResponse, httpRequest, null);
		}


		if (httpRequest.getRequestUrl().equals("/user/form.html") && httpRequest.getHttpMethod().equals("GET")) {
			httpResponse.setLocation("/user/form.html");

			goView(httpResponse, httpRequest, null);
		}

		if (httpRequest.getRequestUrl().equals("/user/create")) {
			Map<String, String> params = httpRequest.getParams();
			// TODO : util
			User user = new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
			DataBase.addUser(user);
			log.debug("[Success addUser] :: userId : {}", user.getUserId());

			goView(httpResponse, httpRequest, null);
		}

		if (httpRequest.getRequestUrl().equals("/user/login.html") && httpRequest.getHttpMethod().equals("GET")) {
			httpResponse.setLocation("/user/login.html");

			goView(httpResponse, httpRequest, null);
		}

		// TODO : 공토변수 뺴기
		// TODO : else if 로 해야 의미가 분명해짐
		// TODO : chaining !
		if (httpRequest.getRequestUrl().equals("/user/login") && httpRequest.getHttpMethod().equals("POST")) {
			Map<String, String> params = httpRequest.getParams();

			User user = DataBase.findUserById(params.get("userId"));
			if (user.getPassword().equals(params.get("password"))) {
				log.debug("[Success login] :: userId : {}", user.getUserId());

				Cookie cookie = new Cookie("logined", "true");
				goView(httpResponse, httpRequest, cookie);
			} else {
				log.debug("[Fail login] :: userId : {}", user.getUserId());

				httpResponse.setLocation("/user/login_failed.html");

				Cookie cookie = new Cookie("logined", "false");
				goView(httpResponse, httpRequest, cookie);
			}

		}

	}

	// TODO : 역할분리
	private void goView(HttpResponse httpResponse, HttpRequest httpRequest, Cookie cookie) throws IOException {
		if (httpResponse.getLocation() == null) {
			File view = new File(WEB_DIRECTORY + DEFAULT_URL);

			// TODO : if 문 밖으로
			byte[] body = Files.readAllBytes(view.toPath());
			response302Header(httpResponse.getDos(), DEFAULT_URL);

			if(cookie != null) {
				setCookie(httpResponse.getDos(), cookie);
			}

			responseLastHeader(httpResponse.getDos());
			responseBody(httpResponse.getDos(), body);
		} else {
			File view = new File(WEB_DIRECTORY + httpResponse.getLocation());
			byte[] body = Files.readAllBytes(view.toPath());
			response200Header(httpResponse.getDos(), body.length);
			if(cookie != null) {
				setCookie(httpResponse.getDos(), cookie);
			}
			responseLastHeader(httpResponse.getDos());
			responseBody(httpResponse.getDos(), body);
		}
	}

	private void setCookie(DataOutputStream dos, Cookie cookie) {
		try {
			dos.writeBytes("Set-Cookie: " + cookie.getName() + "=" + cookie.getValue() + "; \r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void response302Header(DataOutputStream dos, String location) {
		try {
			dos.writeBytes("HTTP/1.1 302 Found \r\n");
			dos.writeBytes("Location: " + location + "\r\n");
//			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
		try {
			dos.writeBytes("HTTP/1.1 200 OK \r\n");
			dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
			dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
//			dos.writeBytes("\r\n");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private void responseLastHeader(DataOutputStream dos) {
		try {
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
