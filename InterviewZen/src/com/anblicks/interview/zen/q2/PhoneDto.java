package com.anblicks.interview.zen.q2;

public class PhoneDto {
	
	private String firstName;
	private String lastName;
	private int phoneNumber;
	
	public PhoneDto(String firstName, String lastName, int phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return firstName + "  "+ lastName + " - " + phoneNumber;
	}
	
	

}
