package next.dao;

import java.util.List;

import common.jdbc.template.JdbcTemplate;
import next.model.Answer;

public class AnswerDao {
	public int insert(Answer answer) {
		JdbcTemplate<Answer> insertJdbcTemplate = new JdbcTemplate<Answer>();

		String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId)"
				+ " VALUES (?, ?, CURRENT_TIMESTAMP(), ?)";

		return insertJdbcTemplate.update(sql, (pstmt) -> {
			pstmt.setString(1, answer.getWriter());
			pstmt.setString(2, answer.getContents());
			pstmt.setInt(3, answer.getQuestionId());
		});
	}

	public List<Answer> findAnswersByQuestionId(int questionId) {
		JdbcTemplate<Answer> selectJdbcTemplate = new JdbcTemplate<Answer>();

		String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId=? ";
		return selectJdbcTemplate.findAll(sql, (rs) -> {
			return new Answer(rs.getInt("answerId"), rs.getString("writer"), rs.getString("contents"),
					rs.getString("createdDate"));
		}, questionId);
	}

	public Answer findAnswerByAnswerId(int answerId) {
		JdbcTemplate<Answer> selectJdbcTemplate = new JdbcTemplate<Answer>();

		String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE answerId=? ";
		return selectJdbcTemplate.findObject(sql, (rs) -> {
			return new Answer(rs.getInt("answerId"), rs.getString("writer"), rs.getString("contents"),
					rs.getString("createdDate"));
		}, answerId);
	}

	public int getAnswerCountByQuestionId(int questionId) {
		JdbcTemplate<Integer> selectJdbcTemplate = new JdbcTemplate<Integer>();

		String sql = "SELECT COUNT(answerId) FROM ANSWERS WHERE questionId = ?";
		return selectJdbcTemplate.findObject(sql, (rs) -> {
			return (int) rs.getLong(1);
		}, questionId);
	}

	public void delete(int answerId) {
		JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<Answer>();
		String sql = "DELETE FROM ANSWERS WHERE answerId = ?";
		jdbcTemplate.update(sql, answerId);
	}
}
