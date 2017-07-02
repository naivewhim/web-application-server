package next.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.QuestionDao;
import next.model.Question;
import next.view.ModelAndView;

public class SummariesQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	QuestionDao questionDao = new QuestionDao();
    	
    	List<Question> questionSummaries = questionDao.findSummaryAll();
    	
        return jsonView().addObject("questionSummaries", questionSummaries);
    }
}
