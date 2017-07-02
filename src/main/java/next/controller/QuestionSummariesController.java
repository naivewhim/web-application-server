package next.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

public class QuestionSummariesController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	QuestionDao questionDao = new QuestionDao();
    	
    	List<Question> sampleList = questionDao.findSummaryAll();
    	String json = new Gson().toJson(sampleList);
    	
    	resp.setContentType("application/json;charset=UTF-8");
    	PrintWriter out = resp.getWriter();
    	out.print(json);
    	
        return null;
    }
}
