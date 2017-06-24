package common.jdbc.template;

import java.sql.SQLException;

import next.dao.UserDao;
import next.model.User;

public interface InsertJdbcTemplate {
	public void insert(User user, UserDao userDao) throws SQLException;
}
