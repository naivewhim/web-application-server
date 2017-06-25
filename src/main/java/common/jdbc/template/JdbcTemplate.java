package common.jdbc.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.exception.DataAccessException;
import common.jdbc.util.PreparedStatementSetter;
import common.jdbc.util.RowMapper;
import core.jdbc.ConnectionManager;

public class JdbcTemplate<T> {
	public void update(String sql, PreparedStatementSetter pstmtSetter) {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmtSetter.setValues(pstmt);

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			throw new DataAccessException(exception);
		}
	}

	public void update(String sql, Object... params) {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i, params[i]);
			}

			pstmt.executeUpdate();
		} catch (SQLException exception) {
			throw new DataAccessException(exception);
		}
	}

	public List<T> findAll(String sql, RowMapper<T> rowMapper, PreparedStatementSetter pstmtSetter) {
		ResultSet rs = null;
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmtSetter.setValues(pstmt);

			rs = pstmt.executeQuery();

			List<T> resultList = new ArrayList<T>();
			while (rs.next()) {
				resultList.add(rowMapper.mapRow(rs));
			}

			return resultList;
		} catch (SQLException exception) {
			throw new DataAccessException(exception);
		}
	}

	public List<T> findAll(String sql, RowMapper<T> rowMapper, Object... params) {
		ResultSet rs = null;
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i, params[i]);
			}

			rs = pstmt.executeQuery();

			List<T> resultList = new ArrayList<T>();
			while (rs.next()) {
				resultList.add(rowMapper.mapRow(rs));
			}

			return resultList;
		} catch (SQLException exception) {
			throw new DataAccessException(exception);
		}
	}

	public T findObject(String sql, RowMapper<T> rowMapper, PreparedStatementSetter pstmtSetter) {
		ResultSet rs = null;
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmtSetter.setValues(pstmt);

			rs = pstmt.executeQuery();

			T resultObject = null;
			if (rs.next()) {
				resultObject = rowMapper.mapRow(rs);
			}

			return resultObject;
		} catch (SQLException exception) {
			throw new DataAccessException(exception);
		}
	}

	public T findObject(String sql, RowMapper<T> rowMapper, Object... params) {
		ResultSet rs = null;
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i, params[i]);
			}

			rs = pstmt.executeQuery();

			T resultObject = null;
			if (rs.next()) {
				resultObject = rowMapper.mapRow(rs);
			}

			return resultObject;
		} catch (SQLException exception) {
			throw new DataAccessException(exception);
		}
	}
}
