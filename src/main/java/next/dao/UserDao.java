package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.template.InsertJdbcTemplate;
import common.jdbc.template.UpdateJdbcTemplate;
import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
	public void insert(User user) throws SQLException {
		InsertJdbcTemplate insertJdbcTemplate = new InsertJdbcTemplate() {
			@Override
			public void insert(User user, UserDao userDao) throws SQLException {
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = ConnectionManager.getConnection();
					String sql = createQueryForInsert();
					pstmt = con.prepareStatement(sql);
					setValuesForinsert(user, pstmt);
					
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
		};
		
		insertJdbcTemplate.insert(user, this);
	}

	private String createQueryForInsert() {
		return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
	}

	private void setValuesForinsert(User user, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
	}

	public void update(User user) throws SQLException {
		UpdateJdbcTemplate updateJdbcTemplate = new UpdateJdbcTemplate() {
			@Override
			public void update(User user, UserDao userDao) throws SQLException {
				Connection con = null;
				PreparedStatement pstmt = null;
				
				try {
					con = ConnectionManager.getConnection();
					String sql = createQueryForUpdate();
					pstmt = con.prepareStatement(sql);
					setValuesForUpdate(user, pstmt);
					
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
		};
		
		updateJdbcTemplate.update(user, this);
	}

	private String createQueryForUpdate() {
		return "UPDATE USERS SET password=?, name=?, email=? WHERE userid=?";
	}

	private void setValuesForUpdate(User user, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getUserId());
	}

	public List<User> findAll() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT userId, password, name, email FROM USERS";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			List<User> userList = new ArrayList<User>();
			if (rs.next()) {
				User user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
				userList.add(user);
			}

			return userList;
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

	public User findByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}

			return user;
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
}
