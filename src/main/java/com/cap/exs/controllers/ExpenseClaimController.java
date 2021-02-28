package com.cap.exs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.services.EmployeeService;
import com.cap.exs.services.ExpenseClaimService;

@RestController
public class ExpenseClaimController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ExpenseClaimService expenseClaimService;

}
