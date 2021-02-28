package com.cap.exs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.services.EmployeeService;
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

}
