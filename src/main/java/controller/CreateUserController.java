package controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DataBase;
import model.HttpRequest;
import model.HttpResponse;
import model.User;

public class CreateUserController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);
	
	@Override
	protected void doPost(HttpRequest request, HttpResponse response) {
		Map<String, String> params = request.getParams();
		User user = new User(params.get("userId"), params.get("password"), params.get("name"),
				params.get("email"));
		DataBase.addUser(user);

		log.debug("[Success addUser] :: userId : {}", user.getUserId());

		response.sendRedirect(DEFAULT_URL);
		return;
	}
}
