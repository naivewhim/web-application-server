package common.jdbc.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import next.model.User;

public interface UpdateJdbcTemplate {
	public void update(User user) throws SQLException;
	
	public String createQueryForUpdate();
	
	public void setValuesForUpdate(User user, PreparedStatement pstmt) throws SQLException;
}
