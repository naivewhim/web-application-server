package controller;

import model.HttpRequest;
import model.HttpResponse;

public interface Controller {
	public static final String DEFAULT_URL = "/index.html";
	public static final String WEB_DIRECTORY = "./webapp";
	
	void service(HttpRequest request, HttpResponse response);
}
