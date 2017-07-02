package next.dao;

import java.util.List;

import common.jdbc.template.JdbcTemplate;
import next.model.Question;

public class QuestionDao {
	public void insert(Question question) {
		JdbcTemplate<Question> insertJdbcTemplate = new JdbcTemplate<Question>();

		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createDate) VALUES (?, ?, ?, ?)";
		insertJdbcTemplate.update(sql, (pstmt) -> {
			pstmt.setString(1, question.getWriter());
			pstmt.setString(2, question.getTitle());
			pstmt.setString(3, question.getContents());
			pstmt.setString(4, question.getCreatedDate());
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
}
