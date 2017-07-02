package next.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonView implements View {
	@Override
	public void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String json = new Gson().toJson(model);
    	
    	resp.setContentType("application/json;charset=UTF-8");
    	PrintWriter out = resp.getWriter();
    	out.print(json);
	}
}
