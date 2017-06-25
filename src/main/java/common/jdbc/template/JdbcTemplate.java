package common.jdbc.template;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface JdbcTemplate<T> {
	public void update(String string) throws SQLException;

	public List<T> findAll(String sql) throws SQLException;

	public T findObject(String sql) throws SQLException;

	public T mapRow(ResultSet rs) throws SQLException;

	public void setValues(PreparedStatement pstmt) throws SQLException;
}
