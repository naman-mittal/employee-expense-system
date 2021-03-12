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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Expense;
import com.cap.exs.request.AddExpenseRequest;
import com.cap.exs.request.UpdateExpenseRequest;
import com.cap.exs.services.ExpenseService;

import io.swagger.annotations.Api;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "Expense", tags = { "ExpenseAPI" })
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/expense/expenseCode")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Integer> getAllExpenseCode()
	{
		return expenseService.getAllExpenseCode();
	}
	
	@PostMapping("/expense")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Expense addExpense(@Valid @RequestBody AddExpenseRequest request)
	{
		Expense expense = new Expense();
		expense.setExpenseType(request.getExpenseType());
		expense.setExpenseDescription(request.getExpenseDescription());
		return expenseService.addExpense(expense);
	}
	
	@GetMapping("/expenses")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Expense> getAllExpenses()
	{
		return expenseService.getAllExpenses();
	}
	
	@GetMapping("/expense/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Expense getExpenseByCode(@PathVariable("id") @Positive int expId)
	{
		return expenseService.getExpenseByCode(expId);
	}
	
	@PutMapping("/expense")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Expense updateExpense(@Valid @RequestBody UpdateExpenseRequest request)
	{
		Expense expense = new Expense();
		expense.setExpenseCode(request.getExpenseCode());
		expense.setExpenseType(request.getExpenseType());
		expense.setExpenseDescription(request.getExpenseDescription());
		return expenseService.updateExpense(expense);
	}
	
	@DeleteMapping("/expense/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Expense deleteExpenseByCode(@PathVariable("id") @Positive int expCode)
	{
		return expenseService.deleteExpenseByCode(expCode);
	}
	
	@DeleteMapping("/expenses")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAllExpenses()
	{
		expenseService.deleteAllExpenses();
	}
	
	
	

}
