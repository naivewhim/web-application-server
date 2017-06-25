package common.jdbc.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;

public abstract class JdbcTemplate<T> {
	public void update(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			setValues(pstmt);

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}

	public List<T> findAll(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			List<T> resultList = new ArrayList<T>();
			while (rs.next()) {
				resultList.add(mapRow(rs));
			}

			return resultList;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public T findObject(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			setValues(pstmt);

			rs = pstmt.executeQuery();

			T resultObject = null;
			if (rs.next()) {
				resultObject = mapRow(rs);
			}

			return resultObject;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public abstract T mapRow(ResultSet rs) throws SQLException;

	public abstract void setValues(PreparedStatement pstmt) throws SQLException;
}
