package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.QuestionDao;
import next.view.ModelAndView;

public class HomeController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        QuestionDao questionDao = new QuestionDao();
        req.setAttribute("questionSummaries", questionDao.findSummaryAll());
        
        return jspView("home.jsp");
    }
}
