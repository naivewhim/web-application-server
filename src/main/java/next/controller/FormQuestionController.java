package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import next.dao.QuestionDao;
import next.view.ModelAndView;

public class FormQuestionController extends AbstractController {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
		String pathVaribale = (String) req.getAttribute("pathVariable");
		if (pathVaribale == null) {
			return jspView("/qna/form.jsp");
		}
		
		int questionId = (Integer.parseInt(pathVaribale));
		QuestionDao questionDao = new QuestionDao();
		return jspView("/qna/form.jsp").addObject("question", questionDao.findByQuestionId(questionId));
	}
}
