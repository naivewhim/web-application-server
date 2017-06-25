package common.jdbc.template;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SelectJdbcTemplate<T> {
	public List<T> findAll(String sql) throws SQLException;
	
	public T findObject(String sql) throws SQLException;
	
	public void setValules(PreparedStatement pstmt) throws SQLException;
	
	public T mapRow(ResultSet rs) throws SQLException;
}
