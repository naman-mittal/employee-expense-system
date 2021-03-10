package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.UsernameAlreadyExistException;
import com.cap.exs.repos.IEmployeeRepository;
import com.cap.exs.services.EmployeeService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEmployeeService {

	@Autowired
	EmployeeService employeeService;


	@MockBean
	IEmployeeRepository employeeRepository;
	
	//@Test
	public void testAddEmployee() {
		
		Employee emp = new Employee();
		
		emp.setEmpName("John");
		emp.setEmpEmailId("naman");
		
		LoginDetails ld = new LoginDetails("test3", "test", "tester");
		
		emp.setLoginDetails(ld);
		
		when(employeeRepository.save(emp)).thenReturn(emp);
		
		assertEquals(emp, employeeService.addEmployee(emp));
		
	}

	@Test(expected = UsernameAlreadyExistException.class)
		public void testAddEmployeeWithExistingUsername() {
			
			Employee emp = new Employee();
			emp.setEmpName("John");
			
			LoginDetails ld = new LoginDetails("test2", "test", "tester");
			emp.setLoginDetails(ld);
			
			when(employeeRepository.save(emp)).thenThrow(UsernameAlreadyExistException.class);
			
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
		Employee emp = new Employee("Naman", null, null, null, null, null, null, null, new LoginDetails());
		emp.setEmpId(1);
		when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));
		assertEquals(emp,employeeService.findByEmployeeCode(2));
		
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void testFindNonExistingEmployee()
	{
		when(employeeRepository.findById(100)).thenThrow(EmployeeNotFoundException.class);
		Employee emp = employeeService.findByEmployeeCode(100);
		System.out.println(emp);
		
	}
	
	@Test
	public void testGetEmployees()
	{
		
		Employee emp1 = new Employee();
		emp1.setEmpName("naman");
		Employee emp2 = new Employee();
		emp2.setEmpName("aman");
		
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp1,emp2));
		assertEquals(2, employeeService.getEmployees().size());
	}
	
	@Test
	public void testDeleteEmployeeById()
	{
		Employee emp = new Employee("Naman", null, null, null, null, null, null, null, new LoginDetails());
		emp.setEmpId(1);
		when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));
		employeeService.deleteEmpById(1);
		
		verify(employeeRepository,times(1)).delete(emp);
		
	}
	
	@Test(expected = EmployeeNotFoundException.class)
	public void testDeleteNonExistingEmployee()
	{
		Employee emp = new Employee("Naman", null, null, null, null, null, null, null, new LoginDetails());
		emp.setEmpId(100);
		when(employeeRepository.findById(emp.getEmpId())).thenThrow(EmployeeNotFoundException.class);
		employeeService.deleteEmpById(emp.getEmpId());
		
		verify(employeeRepository,times(1)).delete(emp);
	}
	
}
