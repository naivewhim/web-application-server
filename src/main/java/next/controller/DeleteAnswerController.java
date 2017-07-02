package next.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.mvc.Controller;
import next.dao.AnswerDao;

public class DeleteAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	int answerId = (Integer.parseInt((String) req.getAttribute("pathVariable")));
    	
        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);
        
    	String json = new Gson().toJson("success");
    	
    	resp.setContentType("application/json;charset=UTF-8");
    	PrintWriter out = resp.getWriter();
    	out.print(json);
    	
        return null;
    }
}
