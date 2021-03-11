 package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.ExpenseClaimAssociatedException;
import com.cap.exs.exceptions.UsernameAlreadyExistException;
import com.cap.exs.repos.IEmployeeRepository;
import com.cap.exs.repos.ILoginRepository;
import com.cap.exs.service_interfaces.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Autowired
	ILoginRepository loginRepository;
	
	@Autowired
	LoginService loginService;
	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	public Employee addEmployee(Employee employee) {
		
		LoginDetails loginDetails = loginRepository.findByUserName(employee.getLoginDetails().getUserName());
		
		if(loginDetails!=null)
		{
			logger.error("username " + loginDetails.getUserName() + " already taken!!", UsernameAlreadyExistException.class);
			throw new UsernameAlreadyExistException("username " + loginDetails.getUserName() + " already exist!!");
		}
		
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getEmployees(){
		
		List<Employee> employees = employeeRepository.findAll();
		
		if(employees.isEmpty())
		{
			logger.error("no employees found!!",EmployeeNotFoundException.class);
			throw new EmployeeNotFoundException("no employees found!!");
		}
		
		return employees;
		
	}
	
	public Employee findByEmployeeCode(int empId) {
		
		Optional<Employee> employee = employeeRepository.findById(empId);
		
		if(!employee.isPresent())
		{
			logger.error("no employee found with id = " + empId,EmployeeNotFoundException.class);
			throw new EmployeeNotFoundException("no employee found with id = " + empId);
		}
		
		return employee.get();
		
	}
	
	public void deleteEmpById(int empId) {
		
		Employee employee = this.findByEmployeeCode(empId);
		
		try
		{
		employeeRepository.delete(employee);
		}
		catch(DataIntegrityViolationException e)
		{
			logger.error("expense claim exist for employee = " + employee,ExpenseClaimAssociatedException.class);
			throw new ExpenseClaimAssociatedException("expense claim exist for employee = " + employee);
		}
		
	}
	
	public Employee updateEmployee(Employee employee) {
		
		
		return employeeRepository.save(employee);
	}
	
	public Employee getDetailsByAll(String username, String password, String role) {
		
		LoginDetails loginDetails = new LoginDetails(username,password,role);
		
		LoginDetails foundLoginDetails = loginService.validateUser(loginDetails);
		
		Employee employee = employeeRepository.findByLoginDetails(foundLoginDetails);
		
		if(employee==null)
		{
			logger.error("no employee found with username = " + username + " AND password = " + password + " AND role = " + role,EmployeeNotFoundException.class);
			throw new EmployeeNotFoundException("no employee found with username = " + username + " AND password = " + password + " AND role = " + role);
		}
		
		return employee;
	}
	
}
