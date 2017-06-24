package next.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;

public class CreateUserController implements Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        LOGGER.debug("User : {}", user);

        UserDao	userDao = new UserDao();
        try {
        	userDao.insert(user);	
        } catch (SQLException exception) {
        	LOGGER.error(exception.getMessage());
        }
        
        return "redirect:/";
    }
}
