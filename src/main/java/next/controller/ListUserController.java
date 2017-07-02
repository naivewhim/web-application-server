package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.UserDao;
import next.session.UserSession;
import next.view.ModelAndView;

public class ListUserController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        if (!UserSession.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

        UserDao userDao = new UserDao();
        req.setAttribute("users", userDao.findAll());
        
        return jspView("/user/list.jsp");
    }
}
