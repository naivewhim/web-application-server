package next.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;

public class UpdateUserController implements Controller {
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		User user = DataBase.findUserById(req.getParameter("userId"));
		if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}

		User updateUser = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
				req.getParameter("email"));
		LOGGER.debug("Update User : {}", updateUser);

		UserDao userDao = new UserDao();
		try {
			userDao.update(updateUser);	
		} catch (SQLException exception) {
			LOGGER.error(exception.getMessage());
		}

		return "redirect:/";
	}
}
