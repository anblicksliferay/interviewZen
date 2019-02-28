package com.grootan.v1.SBRest.dto;


public class QuestionAndAnswersTemplateDto {	
	
	private int id;
	private String Question;
	private String Answer;

	
	public QuestionAndAnswersTemplateDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionAndAnswersTemplateDto(int id, String question, String answer) {
		this.id = id;
		Question = question;
		Answer = answer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	@Override
	public String toString() {
		return "QuestionAndAnswersTemplateDto [id=" + id + ", Question=" + Question + ", Answer=" + Answer + "]";
	}
	
	
	
	
	
}
