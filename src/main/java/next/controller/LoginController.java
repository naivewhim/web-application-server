package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.mvc.AbstractController;
import next.dao.UserDao;
import next.model.User;
import next.session.UserSession;
import next.view.ModelAndView;

public class LoginController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);
        
        if (user == null) {
            req.setAttribute("loginFailed", true);
            return jspView("/user/login.jsp");
        }
        
        if (user.matchPassword(password)) {
            HttpSession session = req.getSession();
            session.setAttribute(UserSession.USER_SESSION_KEY, user);
            return jspView("redirect:/");
        } else {
            req.setAttribute("loginFailed", true);
            return jspView("/user/login.jsp");
        }
    }
}
