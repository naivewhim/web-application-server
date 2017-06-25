package common.jdbc.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import next.model.User;

public interface JdbcTemplate {
	public void update(String string) throws SQLException;

	public void setValues(PreparedStatement pstmt) throws SQLException;
}
