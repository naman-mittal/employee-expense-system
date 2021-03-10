package com.cap.exs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmployeeAssociatedException;
import com.cap.exs.exceptions.InvalidUserException;
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
	LoginDetails loginDetails = loginRepository.findByUserName(details.getUserName());
	
	if(loginDetails!=null)
	{
		throw new UsernameAlreadyExistException("username " + loginDetails.getUserName() + " already exist!!");
	}
	
	return loginRepository.save(details);
	}	
	
	
	
public void deleteDetailsById(int Id) {

	LoginDetails details = loginRepository.findById(Id);
	
	try
	{
	loginRepository.delete(details);
	}
	catch(DataIntegrityViolationException e)
	{
		throw new EmployeeAssociatedException("employee exist with loginDetails = " + details);
	}
	
}


public LoginDetails validateUser(LoginDetails details) {
	LoginDetails foundDetails = loginRepository.validateUser(details.getUserName(), details.getPassword(), details.getRole());
	
	if(foundDetails == null)
	{
		throw new InvalidUserException("loginDetails does not exist");
	}
	
	return foundDetails;
}

//public LoginDetails logout(LoginDetails details) {
//	return details;
//}

}