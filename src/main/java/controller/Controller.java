package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	public static final String DEFAULT_URL = "/index.html";
	public static final String WEB_DIRECTORY = "./webapp";

	String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
