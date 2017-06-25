package common.jdbc.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.util.PreparedStatementSetter;
import common.jdbc.util.RowMapper;
import core.jdbc.ConnectionManager;

public class JdbcTemplate<T> {
	public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmtSetter.setValues(pstmt);

			pstmt.executeUpdate();
		}
	}

	public List<T> findAll(String sql, RowMapper<T> rowMapper) throws SQLException {
		ResultSet rs = null;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			rs = pstmt.executeQuery();

			List<T> resultList = new ArrayList<T>();
			while (rs.next()) {
				resultList.add(rowMapper.mapRow(rs));
			}

			return resultList;
		}
	}

	public T findObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
		ResultSet rs = null;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmtSetter.setValues(pstmt);

			rs = pstmt.executeQuery();

			T resultObject = null;
			if (rs.next()) {
				resultObject = rowMapper.mapRow(rs);
			}

			return resultObject;
		}
	}
}
