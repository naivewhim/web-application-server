package common.jdbc.util;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
	public T mapRow(ResultSet rs) throws SQLException;
}
