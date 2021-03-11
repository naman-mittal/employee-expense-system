package com.cap.exs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.exs.controllers.LoggingController;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmployeeAssociatedException;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.ExpenseClaimAssociatedException;
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
	
	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	//method to add details of the employee
	public LoginDetails addDetails(LoginDetails details) {
	LoginDetails loginDetails = loginRepository.findByUserName(details.getUserName());
	
	if(loginDetails!=null)
	{
		logger.error("username " + loginDetails.getUserName() + " already taken!!", UsernameAlreadyExistException.class);
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
	{	logger.error("expense claim exist for employee = " + details,ExpenseClaimAssociatedException.class);
		throw new EmployeeAssociatedException("employee exist with loginDetails = " + details);
	}
	
}


public LoginDetails validateUser(LoginDetails details) {
	LoginDetails foundDetails = loginRepository.validateUser(details.getUserName(), details.getPassword(), details.getRole());
	
	if(foundDetails == null)
	{	
		logger.error("no employee found with details = " + foundDetails,EmployeeNotFoundException.class);
		throw new InvalidUserException("loginDetails does not exist");
	}
	
	return foundDetails;
}

//public LoginDetails logout(LoginDetails details) {
//	return details;
//}

}