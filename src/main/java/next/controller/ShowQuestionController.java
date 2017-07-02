package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.view.ModelAndView;

public class ShowQuestionController extends AbstractController {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		int questionId = (Integer.parseInt((String) req.getAttribute("pathVariable")));

		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();

		return jspView("/qna/show.jsp").addObject("question", questionDao.findByQuestionId(questionId))
				.addObject("answers", answerDao.findAnswersByQuestionId(questionId))
				.addObject("answerCount", answerDao.getAnswerCountByQuestionId(questionId));
	}
}
