package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.repos.IEmployeeRepository;
import com.cap.exs.services.EmployeeService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEmployeeService {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	IEmployeeRepository employeeRepository;
	
	@Test
	public void testAddEmployee() {
		
		Employee emp = new Employee();
		
		emp.setEmpName("John");
		emp.setEmpEmailId("naman");
		
		LoginDetails ld = new LoginDetails("test3", "test", "tester");
		
		emp.setLoginDetails(ld);
		
		employeeService.addEmployee(emp);
		
		assertEquals(3, employeeRepository.count());
		
	}

	//@Test(expected = UsernameAlreadyExistException.class)
		public void testAddEmployeeWithExistingUsername() {
			
			Employee emp = new Employee();
			emp.setEmpName("John");
			
			LoginDetails ld = new LoginDetails("test2", "test", "tester");
			
			emp.setLoginDetails(ld);
			
			employeeService.addEmployee(emp);
			
		}
	
	//@Test(expected = NullPointerException.class)
	public void testAddEmployeeWithoutLoginDetails() {
		
		Employee emp = new Employee();
		emp.setEmpName("Danny");
		
		employeeService.addEmployee(emp);
		
	}
	
	//@Test
	public void testFindByEmployeeCode()
	{
		Employee emp = employeeService.findByEmployeeCode(1);
		assertNotNull(emp);
		
	}

	//@Test(expected = EmployeeNotFoundException.class)
	public void testFindNonExistingEmployee()
	{
		Employee emp = employeeService.findByEmployeeCode(100);
		
		
	}
	
	//@Test
	public void testGetEmployees()
	{
		List<Employee> employees = employeeService.getEmployees();
		assertEquals(3, employees.size());
	}
	
	//@Test
	public void testDeleteEmployeeById()
	{
		employeeService.deleteEmpById(1);
		assertEquals(2, employeeRepository.count());
	}
	
	//@Test(expected = EmployeeNotFoundException.class)
	public void testDeleteNonExistingEmployee()
	{
		employeeService.deleteEmpById(100);
	}
	
}
