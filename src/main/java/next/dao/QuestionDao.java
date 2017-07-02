package next.dao;

import java.util.List;

import common.jdbc.template.JdbcTemplate;
import next.model.Question;

public class QuestionDao {
	public void insert(Question question) {
		JdbcTemplate<Question> insertJdbcTemplate = new JdbcTemplate<Question>();

		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP())";
		insertJdbcTemplate.update(sql, (pstmt) -> {
			pstmt.setString(1, question.getWriter());
			pstmt.setString(2, question.getTitle());
			pstmt.setString(3, question.getContents());
		});
	}

	public List<Question> findSummaryAll() {
		JdbcTemplate<Question> selectJdbcTemplate = new JdbcTemplate<Question>();

		String sql = "SELECT questionId, writer, title, createdDate FROM QUESTIONS";
		return selectJdbcTemplate.findAll(sql, (rs) -> {
			return new Question(rs.getInt("questionId"), rs.getString("writer"), rs.getString("title")
					, rs.getString("createdDate"));
		});
	}
	
	public Question findByQuestionId(int questionId) {
		JdbcTemplate<Question> selectJdbcTemplate = new JdbcTemplate<Question>();

		String sql = "SELECT questionId, writer, title, contents, createdDate "
				 + "FROM QUESTIONS WHERE questionId=?";
		return selectJdbcTemplate.findObject(sql, (rs) -> {
			return new Question(rs.getInt("questionId"), rs.getString("writer"), rs.getString("title")
					, rs.getString("contents"), rs.getString("createdDate"));
		}, (pstmt) -> {
			pstmt.setInt(1, questionId);
		});
	}
	
	public void delete(int questionId) {
		JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<Question>();
		String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
		jdbcTemplate.update(sql, questionId);
	}
}
