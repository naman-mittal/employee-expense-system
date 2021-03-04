package com.cap.employeeexpensesystem.repos;

import org.springframework.stereotype.Repository;

import com.cap.employeeexpensesystem.entities.LoginDetails;

@Repository
public interface ILoginRepository{
	
	
	
	public LoginDetails addDetails(LoginDetails details);
	
	public int deleteDetailsById(int id); 

	public LoginDetails validateUser(LoginDetails details);
	
	public LoginDetails logout(LoginDetails details);
	
}