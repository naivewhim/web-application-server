package controller;

import model.HttpRequest;
import model.HttpResponse;

public class ResourceController extends AbstractController {
	@Override
	protected void doGet(HttpRequest request, HttpResponse response) {
		response.forward(request.getRequestPath());
		return;
	}
}
