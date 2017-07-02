package next.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;

public class CreateAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents")
    			, Integer.parseInt(req.getParameter("questionId")));
        
        AnswerDao answerDao = new AnswerDao();
        int answerId = answerDao.insert(answer);
        Answer generatedAnswer = answerDao.findAnswerByAnswerId(answerId);
        
    	String json = new Gson().toJson(generatedAnswer);
    	
    	resp.setContentType("application/json;charset=UTF-8");
    	PrintWriter out = resp.getWriter();
    	out.print(json);
    	
    	System.out.println("testtt" + json);
    	
        return null;
    }
}
