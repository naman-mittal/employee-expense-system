package com.cap.exs;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.Expense;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.Project;
import com.cap.exs.repos.IExpenseClaimRepository;
import com.cap.exs.services.EmployeeService;
import com.cap.exs.services.ExpenseClaimService;
import com.cap.exs.services.ExpenseService;
import com.cap.exs.services.ProjectService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestExpenseClaimService {
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	@Autowired
	IExpenseClaimRepository expenseClaimRepository;
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	EmployeeService employeeService;

//	@Test
	public void testAddExpenseClaim() {
		ExpenseClaim expenseClaim = new ExpenseClaim();
		
		expenseClaim.setExpenseAmount(1000.0);
		
		LocalDate startDate = LocalDate.of(2021, 01, 01);
		expenseClaim.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 05, 20);
		expenseClaim.setEndDate(endDate);
		
		Expense expense = new Expense();
		expense = expenseService.getExpenseByCode(1);
		expenseClaim.setExpense(expense);
		
		Project project = new Project();
		project = projectService.findByCode(100);
		expenseClaim.setProject(project);
		
		Employee employee = new Employee();
		employee = employeeService.findByEmployeeCode(2);
		expenseClaim.setEmployee(employee);
		
		expenseClaimService.addExpenseClaim(expenseClaim);
		assertEquals(2, expenseClaimRepository.count());	
	}
	
//	@Test
	public void testGetAllExpenseClaim() {
		List<ExpenseClaim> allExpenseClaim = expenseClaimService.getAllExpenseClaim();
		assertEquals(4, allExpenseClaim.size());	
	}
	
//	@Test
	public void testFetchExpenseClaimById() {
		ExpenseClaim expenseClaim = expenseClaimService.fetchExpenseClaimById(1);
		assertNotNull(expenseClaim);
	}
	
//	@Test
//	public void testUpdateExpenseClaim() {
//		
//	}
	
	
//	@Test
	public void testDeleteExpenseClaimById() {
		expenseClaimService.deleteExpenseClaimById(1);
		assertEquals(1, expenseClaimRepository.count());
	}
	
//	@Test
//	public void testGetAllClaimsByEmployee() {
//		
//	}

}
