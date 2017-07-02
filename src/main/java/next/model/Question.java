package next.model;

public class Question {
	private int questionId;
	private String writer;
	private String title;
	private String contents;
	private String createdDate;

	public Question(int questionId, String writer, String title, String createdDate) {
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.createdDate = createdDate;
	}

	public Question(int questionId, String writer, String title, String contents, String createdDate) {
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = createdDate;
	}

	public int getQuestionId() {
		return questionId;
	}

	public String getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public String getCreatedDate() {
		return createdDate;
	}
}
