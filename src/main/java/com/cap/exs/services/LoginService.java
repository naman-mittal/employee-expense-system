package com.cap.exs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.UsernameAlreadyExistException;
import com.cap.exs.repos.IEmployeeRepository;
import com.cap.exs.repos.ILoginRepository;
import com.cap.exs.service_interfaces.ILoginService;

@Service
public class LoginService implements ILoginService{

	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Autowired
	ILoginRepository loginRepository;
	
	@Autowired
	LoginService loginService;
	

	
	//method to add details of the employee
	public LoginDetails addDetails(LoginDetails details) {
	LoginDetails loginDetails = loginRepository.findByUsername(details.getUserName());
	
	if(loginDetails!=null)
	{
		throw new UsernameAlreadyExistException("username " + loginDetails.getUserName() + " already exist!!");
	}
	
	return loginRepository.save(details);
	}	
	
	
	
//public void deleteDetailsById(int empId) {
//	LoginDetails details = this.findByEmployeeCode(empId);
//	
//	employeeRepository.delete(details);
//	
//}


public LoginDetails validateUser(LoginDetails details) {
	LoginDetails details = this.findByEmployeeCode(empId);
	
	return null;
}


//public LoginDetails logout(LoginDetails details) {
//	return details;
//}

}