package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;

public class QuestionDetailController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
    	int questionId = (Integer.parseInt((String) req.getAttribute("pathVariable")));

    	QuestionDao questionDao = new QuestionDao();
        req.setAttribute("question", questionDao.findByQuestionId(questionId));
        
        return "/qna/show.jsp";
    }
}
