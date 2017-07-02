package next.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.QuestionDao;
import next.view.ModelAndView;

public class QuestionDeleteController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	int questionId = (Integer.parseInt((String) req.getAttribute("pathVariable")));
    	
        QuestionDao questionDao = new QuestionDao();
        questionDao.delete(questionId);
    	
        return jspView("redirect:/");
    }
}
