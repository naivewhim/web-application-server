package next.dao;

import java.util.List;

import common.jdbc.template.JdbcTemplate;
import next.model.User;

public class UserDao {
	public void insert(User user) {
		JdbcTemplate<User> insertJdbcTemplate = new JdbcTemplate<User>();

		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		insertJdbcTemplate.update(sql, (pstmt) -> {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
		});
	}

	public void update(User user) {
		JdbcTemplate<User> updateJdbcTemplate = new JdbcTemplate<User>();

		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userid=?";
		updateJdbcTemplate.update(sql, (pstmt) -> {
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());
		});
	}

	public List<User> findAll() {
		JdbcTemplate<User> selectJdbcTemplate = new JdbcTemplate<User>();

		String sql = "SELECT userId, password, name, email FROM USERS";
		return selectJdbcTemplate.findAll(sql, (rs) -> {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
					rs.getString("email"));
		});
	}

	public User findByUserId(String userId) {
		JdbcTemplate<User> selectJdbcTemplate = new JdbcTemplate<User>();

		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		return selectJdbcTemplate.findObject(sql, (rs) -> {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
					rs.getString("email"));
		}, (pstmt) -> {
			pstmt.setString(1, userId);
		});
	}
}
