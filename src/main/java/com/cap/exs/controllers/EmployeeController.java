package com.cap.exs.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Employee;
import com.cap.exs.request.UpdateEmployeeRequest;
import com.cap.exs.services.EmployeeService;

import io.swagger.annotations.Api;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "Employee", tags = { "EmployeeAPI" })
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	// Add a new Employee
	
	@PostMapping("/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee addEmployee(@Valid @RequestBody Employee employee) {
		
		return employeeService.addEmployee(employee);
	}
	
	// Get all the employees
	
	@GetMapping("/employees")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Employee> getEmployees(){
		
		return employeeService.getEmployees();
	}
	
	// Find an employee by its id
	
	@GetMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee findByEmployeeCode(@PathVariable("id") @Min(1) int empId) {
		
		return employeeService.findByEmployeeCode(empId);		
	}
	
	// delete an employee by its id
	
	@DeleteMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteEmpById(@PathVariable("id") @Min(1) int empId) {
		
		employeeService.deleteEmpById(empId);
	}
	
	// update employee's designation,domain and PAN
	
	@PutMapping("/employee")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Employee updateEmployee(@Valid @RequestBody UpdateEmployeeRequest request) {
		
		Employee employee = new Employee();
		
		employee.setEmpId(request.getId());
		employee.setEmpDesignation(request.getDesignation());
		employee.setEmpDomain(request.getDomain());
		employee.setEmpPAN(request.getPan());
		
		return employeeService.updateEmployee(employee);
	}
	
	// Get employee by its username, password and role
	
	@GetMapping("/employee")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee getDetailsByAll(@RequestParam(name = "userName") String username, @RequestParam(name = "password") String password, @RequestParam(name = "role") String role) {
		
		return employeeService.getDetailsByAll(username, password, role);
	}
	
	
}
