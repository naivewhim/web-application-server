package next.model;

public class Answer {
	private int answerId;
	private String writer;
	private String contents;
	private String createdDate;
	private int questionId;
	
	public Answer(int answerId, String writer, String contents, String createdDate) {
		super();
		this.answerId = answerId;
		this.writer = writer;
		this.contents = contents;
		this.createdDate = createdDate;
	}
	
	public Answer(String writer, String contents, int questionId) {
		super();
		this.writer = writer;
		this.contents = contents;
		this.questionId = questionId;
	}
	
	public int getAnswerId() {
		return answerId;
	}
	public String getWriter() {
		return writer;
	}
	public String getContents() {
		return contents;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public int getQuestionId() {
		return questionId;
	}
}
