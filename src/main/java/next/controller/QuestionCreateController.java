package next.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.QuestionDao;
import next.model.Question;
import next.view.ModelAndView;

public class QuestionCreateController extends AbstractController {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Question question = new Question(req.getParameter("writer"), req.getParameter("title"),
				req.getParameter("contents"));

		QuestionDao questionDao = new QuestionDao();
		questionDao.insert(question);

		return jspView("redirect:/");
	}
}
