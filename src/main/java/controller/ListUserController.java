package controller;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.HttpRequest;
import model.HttpResponse;
import model.User;
import util.HttpRequestUtil;

public class ListUserController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(ListUserController.class);
	
	@Override
	protected void doGet(HttpRequest request, HttpResponse response) {
		String cookies = request.getHeader().get("Cookie");

		Map<String, String> cookieMap = HttpRequestUtil.parseCookies(cookies);

		if ("true".equals(cookieMap.get("logined"))) {
			StringBuilder sb = new StringBuilder();

			Collection<User> userList = DataBase.findAll();
			for (User user : userList) {
				sb.append(user.toString());
				sb.append("\r\n");
			}

			response.forwardBody(sb.toString());
			return;
		} else {
			log.info("user not logined");
			
			response.sendRedirect("/user/login.html");
			return;
		}
	}
}
