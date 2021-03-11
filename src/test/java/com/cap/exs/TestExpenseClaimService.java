package com.cap.exs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.LoginDetails;
import com.cap.exs.repos.IExpenseClaimRepository;
import com.cap.exs.services.ExpenseClaimService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestExpenseClaimService {
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	@MockBean
	IExpenseClaimRepository expenseClaimRepository;

//	@Test
	public void testAddExpenseClaim() {
		ExpenseClaim expenseClaim = new ExpenseClaim();
		
		expenseClaim.setExpenseAmount(1000.0);
		
		LocalDate startDate = LocalDate.of(2021, 01, 01);
		expenseClaim.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 05, 20);
		expenseClaim.setEndDate(endDate);
		
//		Expense expense = new Expense(1,"abc","okok");
//		expenseClaim.setExpense(expense);
//		
//		Project project = new Project(1, "Website", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 05, 20));
//		expenseClaim.setProject(project);
//		
//		Employee employee = new Employee("Naman", null, null, null, null, null, null, null, new LoginDetails());
//		employee.setEmpId(1);
//		expenseClaim.setEmployee(employee);
		
		when(expenseClaimRepository.save(expenseClaim)).thenReturn(expenseClaim);
		assertEquals(expenseClaim, expenseClaimService.addExpenseClaim(expenseClaim));	
	}
	
//	@Test
	public void testGetAllExpenseClaim() {
		ExpenseClaim expenseClaim1 = new ExpenseClaim();

		expenseClaim1.setExpenseAmount(1000.0);
		
		LocalDate startDate1 = LocalDate.of(2021, 01, 01);
		expenseClaim1.setStartDate(startDate1);
		
		LocalDate endDate1 = LocalDate.of(2021, 05, 20);
		expenseClaim1.setEndDate(endDate1);
		
		ExpenseClaim expenseClaim2 = new ExpenseClaim();

		expenseClaim2.setExpenseAmount(2000.0);
		
		LocalDate startDate = LocalDate.of(2021, 05, 01);
		expenseClaim2.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 01, 20);
		expenseClaim2.setEndDate(endDate);
		
		when(expenseClaimRepository.findAll()).thenReturn(Arrays.asList(expenseClaim1,expenseClaim2));
		assertEquals(2, expenseClaimService.getAllExpenseClaim().size());	
	}
	
//	@Test
	public void testFetchExpenseClaimById() {
		ExpenseClaim expenseClaim = new ExpenseClaim();
		
		expenseClaim.setExpenseAmount(1000.0);
		
		LocalDate startDate = LocalDate.of(2021, 01, 01);
		expenseClaim.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 05, 20);
		expenseClaim.setEndDate(endDate);
		
		expenseClaim.setExpenseCodeId(1);
		
		when(expenseClaimRepository.findById(1)).thenReturn(Optional.of(expenseClaim));
		assertEquals(expenseClaim,expenseClaimService.fetchExpenseClaimById(1));
	}
	
//	@Test
//	public void testUpdateExpenseClaim() {
//		
//	}
	
	
//	@Test
	public void testDeleteExpenseClaimById() {
		ExpenseClaim expenseClaim = new ExpenseClaim();
		
		expenseClaim.setExpenseAmount(1000.0);
		
		LocalDate startDate = LocalDate.of(2021, 01, 01);
		expenseClaim.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 05, 20);
		expenseClaim.setEndDate(endDate);
		
		expenseClaim.setExpenseCodeId(1);
		
		when(expenseClaimRepository.findById(1)).thenReturn(Optional.of(expenseClaim));
		expenseClaimService.deleteExpenseClaimById(1);
		
		verify(expenseClaimRepository,times(1)).delete(expenseClaim);
	}
	
//	@Test
	public void testGetAllClaimsByEmployee() {
		ExpenseClaim expenseClaim1 = new ExpenseClaim();
		
		expenseClaim1.setExpenseAmount(1000.0);
		
		LocalDate startDate1 = LocalDate.of(2021, 01, 01);
		expenseClaim1.setStartDate(startDate1);
		
		LocalDate endDate1 = LocalDate.of(2021, 05, 20);
		expenseClaim1.setEndDate(endDate1);
		
		ExpenseClaim expenseClaim2 = new ExpenseClaim();
		
		expenseClaim2.setExpenseAmount(2000.0);
		
		LocalDate startDate = LocalDate.of(2021, 05, 01);
		expenseClaim2.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 01, 20);
		expenseClaim2.setEndDate(endDate);
		
		Employee employee = new Employee("Naman", null, null, null, null, null, null, null, new LoginDetails());
		employee.setEmpId(1);
		expenseClaim1.setEmployee(employee);
		expenseClaim2.setEmployee(employee);
		
		when(expenseClaimRepository.findAll()).thenReturn(Arrays.asList(expenseClaim1,expenseClaim2));
		assertEquals(2, expenseClaimService.getAllClaimsByEmployee(employee).size());
	}
	
//	@Test
	public void testFindAllClaimsBetweenDates() {
		ExpenseClaim expenseClaim1 = new ExpenseClaim();
		expenseClaim1.setExpenseCodeId(1);
		expenseClaim1.setExpenseAmount(1000.0);
		
		LocalDate startDate1 = LocalDate.of(2021, 01, 01);
		expenseClaim1.setStartDate(startDate1);
		
		LocalDate endDate1 = LocalDate.of(2021, 05, 20);
		expenseClaim1.setEndDate(endDate1);
		
		ExpenseClaim expenseClaim2 = new ExpenseClaim();
		expenseClaim2.setExpenseCodeId(2);
		expenseClaim2.setExpenseAmount(2000.0);
		
		LocalDate startDate = LocalDate.of(2021, 05, 01);
		expenseClaim2.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 10, 20);
		expenseClaim2.setEndDate(endDate);
		
		when(expenseClaimRepository.findAllBetweenDates(startDate1, endDate)).thenReturn(Arrays.asList(expenseClaim1,expenseClaim2));
		assertEquals(2, expenseClaimService.findAllClaimsBetweenDates(startDate1, endDate).size());
	}

}
