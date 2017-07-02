package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import next.dao.UserDao;
import next.model.User;
import next.view.ModelAndView;

public class CreateUserController extends AbstractController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserController.class);

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
				req.getParameter("email"));
		LOGGER.debug("User : {}", user);

		UserDao userDao = new UserDao();
		if (userDao.findByUserId(user.getUserId()) != null) {
			return jspView("redirect:/error/existUser");
		}

		userDao.insert(user);

		return jspView("redirect:/");
	}
}
