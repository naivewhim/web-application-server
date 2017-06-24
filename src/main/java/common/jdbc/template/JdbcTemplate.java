package common.jdbc.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import next.model.User;

public interface JdbcTemplate {
	public void update(User user) throws SQLException;

	public String createQuery();

	public void setValues(User user, PreparedStatement pstmt) throws SQLException;
}
