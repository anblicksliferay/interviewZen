package com.grootan.v1.SBRest.dto;

public class User {
	
	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;
	private Boolean Status;
	private String Comment;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String firstName, String lastName, String email, String password, Boolean status, String comment) {
		super();
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		Password = password;
		Status = status;
		Comment = comment;
	}

	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public Boolean getStatus() {
		return Status;
	}
	public void setStatus(Boolean status) {
		Status = status;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	@Override
	public String toString() {
		return "User [FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email + ", Password=" + Password
				+ ", Status=" + Status + ", Comment=" + Comment + "]";
	}
	
	
}
