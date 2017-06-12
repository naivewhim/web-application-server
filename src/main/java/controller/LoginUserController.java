package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.User;

public class LoginUserController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(LoginUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		User user = DataBase.findUserById(request.getParameter("userId"));

		if (user == null) {
			return "redirect:" + "/user/login_failed.html";
		}

		if (user.getPassword() != null && user.getPassword().equals(request.getParameter("password"))) {
			log.debug("[Success login] :: userId : {}", user.getUserId());

			// TODO : Set-Cookie: id=a3fWa; Domain=localhost; Path=/; Expires=Wed, 21 Jun 2017 07:28:00 GMT; Secure; HttpOnly
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			return "redirect:" + DEFAULT_URL;
		} else {
			log.debug("[Fail login] :: userId : {}", user.getUserId());

			return "redirect:" + "/user/login_failed.html";
		}
	}
}
