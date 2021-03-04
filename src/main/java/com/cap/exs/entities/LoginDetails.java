package com.cap.employeeexpensesystem.entities;

import javax.persistence.*;

public class LoginDetails {
	
	@SequenceGenerator(name="employee_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "employee_sequence")
	@Id
	private int id;		 //employee id
	private String userName;		//employee username
	private String password;		//employee password
	String role;			//employee role

	//default constructor
	public LoginDetails() {
}

	public LoginDetails(String userName, String password, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}
	
	

}
