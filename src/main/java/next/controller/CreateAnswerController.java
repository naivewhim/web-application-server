package next.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.AnswerDao;
import next.model.Answer;
import next.view.ModelAndView;

public class CreateAnswerController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents")
    			, Integer.parseInt(req.getParameter("questionId")));
        
        AnswerDao answerDao = new AnswerDao();
        int answerId = answerDao.insert(answer);
        Answer generatedAnswer = answerDao.findAnswerByAnswerId(answerId);
    	
        return jsonView().addObject("generatedAnswer", generatedAnswer);
    }
}
