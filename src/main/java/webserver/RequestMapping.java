package webserver;

import java.util.HashMap;
import java.util.Map;

import controller.Controller;
import controller.CreateUserController;
import controller.ListUserController;
import controller.LoginUserController;
import controller.ResourceController;

public class RequestMapping {
	private Map<String, Controller> controllerMap = new HashMap<>();

	void initMapping() {
		controllerMap.put("/user/create", new CreateUserController());
		controllerMap.put("/user/login", new LoginUserController());
		controllerMap.put("/user/list", new ListUserController());
	}

	public Controller getController(String requestURl) {
		Controller controller = controllerMap.get(requestURl);

		return controller == null ? new ResourceController() : controller;
	}

	void put(String url, Controller controller) {
		controllerMap.put(url, controller);
	}
}
