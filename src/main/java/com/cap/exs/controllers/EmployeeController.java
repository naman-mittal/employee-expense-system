package com.cap.exs.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

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
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.request.SignupRequest;
import com.cap.exs.request.UpdateEmployeeRequest;
import com.cap.exs.services.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "Employee", tags = { "EmployeeAPI" })
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	// Add a new Employee
	
	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Signup", response = Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully signed up"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public Employee addEmployee(@ApiParam(name="Signup Request", required = true) @Valid @RequestBody SignupRequest request) {
		
		Employee employee = new Employee();
		LoginDetails loginDetails = new LoginDetails();
		
		employee.setEmpName(request.getName());
		employee.setEmpPAN(request.getPan());
		employee.setEmpDesignation(request.getDesignation());
		employee.setEmpDomain(request.getDomain());
		employee.setEmpDOB(request.getDob());
		employee.setEmpDOJ(request.getDoj());
		employee.setEmpSalary(request.getSalary());
		employee.setEmpEmailId(request.getEmail());
		
		loginDetails.setUserName(request.getUsername());
		loginDetails.setPassword(request.getPassword());
		loginDetails.setRole(request.getRole());
		
		employee.setLoginDetails(loginDetails);
		
		return employeeService.addEmployee(employee);
	}
	
	// Get all the employees
	
	@GetMapping("/employees")
	@ApiOperation(value = "Get all Employees", response = List.class)
	@ResponseStatus(code = HttpStatus.OK)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved all employees"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "No employees found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public List<Employee> getEmployees(){
		
		return employeeService.getEmployees();
	}
	
	// Find an employee by its id
	
	@GetMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Retrieve an employee using its Id", response = Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Employee details"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public Employee findByEmployeeCode(@PathVariable("id") @Positive int empId) {
		
		return employeeService.findByEmployeeCode(empId);		
	}
	
	// delete an employee by its id
	
	@DeleteMapping("/employee/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete an employee by its Id")
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public void deleteEmpById(@PathVariable("id") @Positive int empId) {
		
		employeeService.deleteEmpById(empId);
	}
	
	// update employee's designation,domain and PAN
	
	@PutMapping("/employee")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Update the employee", response = Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public Employee updateEmployee(@ApiParam(name="Update Employee Request", required = true)@Valid @RequestBody UpdateEmployeeRequest request) {
		
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
	@ApiOperation(value = "Retrieve an employee using its username, password and role", response = Employee.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved employee"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public Employee getDetailsByAll(@ApiParam(name="Employee's username", required = true)@RequestParam(name = "userName") String username,@ApiParam(name="Employee's password", required = true) @RequestParam(name = "password") String password,@ApiParam(name="Employee's role", required = true) @RequestParam(name = "role") String role) {
		
		return employeeService.getDetailsByAll(username, password, role);
	}
	
	
}
