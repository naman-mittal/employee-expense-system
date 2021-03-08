package com.cap.exs.controllers;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.services.ExpenseClaimService;

@RestController
public class ExpenseClaimController {
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	@GetMapping("/expensesClaim")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<ExpenseClaim> getAllExpenseClaim(){
		return expenseClaimService.getAllExpenseClaim();
	}
	
	@PostMapping("/expenseClaim")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ExpenseClaim addExpenseClaim(@RequestBody ExpenseClaim expenseClaim) {
		return expenseClaimService.addExpenseClaim(expenseClaim);
	}
	
	@GetMapping("/expenseClaim/{id}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public ExpenseClaim fetchExpenseClaimById(@PathVariable("id") @Min(1) int expenseClaimId){
		return expenseClaimService.fetchExpenseClaimById(expenseClaimId);
	}
	
	@PutMapping("/expenseClaim")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ExpenseClaim updateExpenseClaim(@RequestBody ExpenseClaim expenseClaim) {
		return expenseClaimService.uppdateExpenseClaim(expenseClaim);
	}
	
	@DeleteMapping("/expenseClaim/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ExpenseClaim deleteExpenseClaimById(@PathVariable("id") @Min(1) int expenseClaimId) {
		return expenseClaimService.deleteExpenseClaimById(expenseClaimId);
	}
	
	@GetMapping("/expenseClaim/{Employee}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<ExpenseClaim> getAllClaimsByEmployee(@PathVariable("Employee") Employee emp){
		return expenseClaimService.getAllClaimsByEmployee(emp);
	}

}
