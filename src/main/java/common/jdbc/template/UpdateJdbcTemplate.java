package common.jdbc.template;

import java.sql.SQLException;

import next.dao.UserDao;
import next.model.User;

public interface UpdateJdbcTemplate {
	public void update(User user, UserDao userDao) throws SQLException;
}
