package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.template.JdbcTemplate;
import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
	public void insert(User user) throws SQLException {
		JdbcTemplate<User> insertJdbcTemplate = new JdbcTemplate<User>() {
			@Override
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

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}

			@Override
			public List<User> findAll(String sql) throws SQLException {
				return null;
			}

			@Override
			public User findObject(String sql) throws SQLException {
				return null;
			}

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return null;
			}
		};

		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		insertJdbcTemplate.update(sql);
	}

	public void update(User user) throws SQLException {
		JdbcTemplate<User> updateJdbcTemplate = new JdbcTemplate<User>() {
			@Override
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

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}

			@Override
			public List<User> findAll(String sql) throws SQLException {
				return null;
			}

			@Override
			public User findObject(String sql) throws SQLException {
				return null;
			}

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return null;
			}
		};

		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userid=?";
		updateJdbcTemplate.update(sql);
	}

	public List<User> findAll() throws SQLException {
		JdbcTemplate<User> selectJdbcTemplate = new JdbcTemplate<User>() {
			@Override
			public List<User> findAll(String sql) throws SQLException {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					con = ConnectionManager.getConnection();
					pstmt = con.prepareStatement(sql);

					rs = pstmt.executeQuery();

					List<User> userList = new ArrayList<User>();
					while (rs.next()) {
						userList.add(mapRow(rs));
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

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}

			@Override
			public User findObject(String sql) throws SQLException {
				return null;
			}

			@Override
			public void update(String string) throws SQLException {
			}

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
			}

		};

		String sql = "SELECT userId, password, name, email FROM USERS";
		return selectJdbcTemplate.findAll(sql);
	}

	public User findByUserId(String userId) throws SQLException {
		JdbcTemplate<User> selectJdbcTemplate = new JdbcTemplate<User>() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}

			@Override
			public User findObject(String sql) throws SQLException {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					con = ConnectionManager.getConnection();
					pstmt = con.prepareStatement(sql);
					setValues(pstmt);

					rs = pstmt.executeQuery();

					User user = null;
					if (rs.next()) {
						user = mapRow(rs);
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

			@Override
			public void update(String string) throws SQLException {
			}
			
			@Override
			public List<User> findAll(String sql) throws SQLException {
				return null;
			}
		};

		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		return selectJdbcTemplate.findObject(sql);
	}
}
