package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;

public class ListUserController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(ListUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		if (isLogined(request.getSession())) {
			request.setAttribute("users", DataBase.findAll());
			return "redirect:/users/list.jsp";
		} else {
			log.info("user not logined");

			return "/user/login.html";
		}
	}

	private static boolean isLogined(HttpSession session) {
		Object user = session.getAttribute("user");
		if (user == null) {
			return false;
		}
		return true;
	}
}
