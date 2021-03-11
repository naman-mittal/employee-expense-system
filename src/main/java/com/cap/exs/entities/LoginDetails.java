package com.cap.exs.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
@Entity
public class LoginDetails {
	
	@SequenceGenerator(name="employee_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "employee_sequence")
	@Id
	private int id;		 //employee id
	@Size(min = 4,max = 20)
	private String userName;		//employee username
	
	@Size(min = 8,max = 20)
	private String password;		//employee password
	private String role;			//employee role

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
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginDetails [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}


	
	

}
