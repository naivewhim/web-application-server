package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.UserDao;
import next.model.User;
import next.session.UserSession;
import next.view.ModelAndView;

public class UpdateFormUserController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        
        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);
        
        if (!UserSession.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        req.setAttribute("user", user);
        
        return jspView("/user/updateForm.jsp");
    }
}
