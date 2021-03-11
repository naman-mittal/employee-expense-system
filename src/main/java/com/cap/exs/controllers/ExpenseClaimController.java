package com.cap.exs.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.Expense;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.Project;
import com.cap.exs.request.AddExpenseClaimRequest;
import com.cap.exs.request.UpdateExpenseClaimRequest;
import com.cap.exs.services.ExpenseClaimService;

@RestController
@Validated
public class ExpenseClaimController {
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	@GetMapping("/expenseClaims")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ExpenseClaim> getAllExpenseClaim(){
		return expenseClaimService.getAllExpenseClaim();
	}
	
	@PostMapping("/expenseClaim")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ExpenseClaim addExpenseClaim(@Valid @RequestBody AddExpenseClaimRequest request) {
		
		ExpenseClaim claim = new ExpenseClaim();
		
		claim.setExpenseAmount(request.getAmount());
		claim.setStartDate(request.getStartDate());
		claim.setEndDate(request.getEndDate());
		
		Employee employee = new Employee();
		employee.setEmpId(request.getEmployeeId());
		claim.setEmployee(employee);
		
		Project project = new Project();
		project.setProjectCode(request.getProjectId());
		claim.setProject(project);
		
		Expense expense = new Expense();
		expense.setExpenseCode(request.getExpenseId());
		claim.setExpense(expense);
		
		return expenseClaimService.addExpenseClaim(claim);
	}
	
	@GetMapping("/expenseClaim/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ExpenseClaim fetchExpenseClaimById(@PathVariable("id") @Min(1) int expenseClaimId){
		return expenseClaimService.fetchExpenseClaimById(expenseClaimId);
	}
	
	@PutMapping("/expenseClaim")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ExpenseClaim updateExpenseClaim(@Valid @RequestBody UpdateExpenseClaimRequest request) {
		
		ExpenseClaim expenseClaim = new ExpenseClaim();
		expenseClaim.setExpenseCodeId(request.getId());
		expenseClaim.setStartDate(request.getStartDate());
		expenseClaim.setEndDate(request.getEndDate());
		expenseClaim.setExpenseAmount(request.getExpenseAmount());
		
		return expenseClaimService.updateExpenseClaim(expenseClaim);
	}
	
	@DeleteMapping("/expenseClaim/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ExpenseClaim deleteExpenseClaimById(@PathVariable("id") @Min(1) int expenseClaimId) {
		return expenseClaimService.deleteExpenseClaimById(expenseClaimId);
	}
	
	@GetMapping("/expenseClaims/employee")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ExpenseClaim> getAllClaimsByEmployee(Employee emp){
		return expenseClaimService.getAllClaimsByEmployee(emp);
	}
	
	@GetMapping("/expenseClaims/dates")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ExpenseClaim> findAllClaimsBetweenDates(@RequestParam("startDate")@DateTimeFormat(pattern="MM/dd/yyyy") LocalDate startDate ,@RequestParam("endDate")@DateTimeFormat(pattern="MM/dd/yyyy") LocalDate endDate){
		return expenseClaimService.findAllClaimsBetweenDates(startDate, endDate);
	}

}
