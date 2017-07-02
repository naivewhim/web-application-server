package next.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.AnswerDao;
import next.view.ModelAndView;

public class DeleteAnswerController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	int answerId = (Integer.parseInt((String) req.getAttribute("pathVariable")));
    	
        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);
    	
    	String successCode = "success";
        return jsonView().addObject("successCode", successCode);
    }
}
