package controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.HttpRequest;
import model.HttpResponse;
import model.User;

public class LoginUserController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(LoginUserController.class);
	
	@Override
	protected void doPost(HttpRequest request, HttpResponse response) {
		Map<String, String> params = request.getParams();
		User user = DataBase.findUserById(params.get("userId"));

		if (user == null) {
			response.sendRedirect("/user/login_failed.html");
			return;
		}

		if (user.getPassword() != null && user.getPassword().equals(params.get("password"))) {
			log.debug("[Success login] :: userId : {}", user.getUserId());

			response.addHeader("Set-Cookie", "logined=true; Path=/ ");
			response.sendRedirect(DEFAULT_URL);
			return;
		} else {
			log.debug("[Fail login] :: userId : {}", user.getUserId());

			response.addHeader("Set-Cookie", "logined=false");
			response.sendRedirect("/user/login_failed.html");
			return;
		}
	}
}
