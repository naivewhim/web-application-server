package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import common.jdbc.template.JdbcTemplate;
import common.jdbc.util.PreparedStatementSetter;
import common.jdbc.util.RowMapper;
import next.model.User;

public class UserDao {
	public void insert(User user) throws SQLException {
		JdbcTemplate<User> insertJdbcTemplate = new JdbcTemplate<User>();

		PreparedStatementSetter pstmtSetter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		};

		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		insertJdbcTemplate.update(sql, pstmtSetter);
	}

	public void update(User user) throws SQLException {
		JdbcTemplate<User> updateJdbcTemplate = new JdbcTemplate<User>();

		PreparedStatementSetter pstmtSetter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}
		};

		String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userid=?";
		updateJdbcTemplate.update(sql, pstmtSetter);
	}

	public List<User> findAll() throws SQLException {
		JdbcTemplate<User> selectJdbcTemplate = new JdbcTemplate<User>();

		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};

		String sql = "SELECT userId, password, name, email FROM USERS";
		return selectJdbcTemplate.findAll(sql, rowMapper);
	}

	public User findByUserId(String userId) throws SQLException {
		JdbcTemplate<User> selectJdbcTemplate = new JdbcTemplate<User>();

		PreparedStatementSetter pstmtSetter = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};

		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};

		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		return selectJdbcTemplate.findObject(sql, rowMapper, pstmtSetter);
	}
}
