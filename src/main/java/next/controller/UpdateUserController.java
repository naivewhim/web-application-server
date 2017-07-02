package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import next.dao.UserDao;
import next.model.User;
import next.session.UserSession;
import next.view.ModelAndView;

public class UpdateUserController extends AbstractController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(req.getParameter("userId"));

		if (!UserSession.isSameUser(req.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}

		User updateUser = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
				req.getParameter("email"));
		LOGGER.debug("Update User : {}", updateUser);

		userDao.update(updateUser);

		return jspView("redirect:/");
	}
}
