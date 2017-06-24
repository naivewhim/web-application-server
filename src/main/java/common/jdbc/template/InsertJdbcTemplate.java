package common.jdbc.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import next.model.User;

public interface InsertJdbcTemplate {
	public void insert(User user) throws SQLException;
	
	public String createQueryForInsert();
	
	public void setValuesForinsert(User user, PreparedStatement pstmt) throws SQLException;
}
