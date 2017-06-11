package webserver;

import java.util.HashMap;
import java.util.Map;

import controller.Controller;
import controller.CreateUserController;
import controller.ListUserController;
import controller.LoginUserController;
import controller.ResourceController;

public class RequestMapping {
	private static Map<String, Controller> controllerMap = new HashMap<>();
	
	static {
		controllerMap.put("/user/create", new CreateUserController());
		controllerMap.put("/user/login", new LoginUserController());
		controllerMap.put("/user/list", new ListUserController());
	}
	
	public static Controller getController(String requestURl) {
		Controller controller = controllerMap.get(requestURl);
		return controller == null ? new ResourceController() : controller;
	}
}
