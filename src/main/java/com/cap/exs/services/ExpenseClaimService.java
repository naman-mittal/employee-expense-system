package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cap.exs.entities.Employee;
import com.cap.exs.repos.EmployeeRepository;
import com.cap.exs.repos.ExpenseClaim;
import com.cap.exs.repos.ExpenseClaimRepository;

public class ExpenseClaimService {
	@Autowired
	ExpenseClaimRepository expenseClaimRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	public ExpenseClaim addExpenseClaim(ExpenseClaim expenseClaim) {
		
		return expenseClaimRepository.save(ExpenseClaim);
	}
	public List<ExpenseClaim> getAllExpenseClaim(){
		
		List<ExpenseClaim> expenseClaims = expenseClaimRepository.findAll();
		
		if(expenseClaim.isEmpty())
		{
			//throw exception
		}
		
		return expenseClaim;
		
	}
	
	public ExpenseClaim findExpenseClaimById(int expenseCodeID) {
		
		Optional<ExpenseClaim> expenseClaim = expenseClaimRepository.findById(empId);
		if(!expenseClaim.isPresent())
		{
			//throw exception
		}
		
		return expenseClaim;
	
	}
	
	public ExpenseClaim uppdateExpenseClaim(ExpenseClaim expenseClaim) {
		
		return null;
		
	}
	
	public ExpenseClaim deleteExpenseClaimById(int id) {
		
		ExpenseClaim expenseClaim = this.findExpenseClaimById(id);
		
		expenseClaimRepository.delete(expenseClaim);
		
	}
	
	public List<ExpenseClaim> getAllClaimsByEmployee(Employee employee){
		//LOGIC
		
		return null;
	}


	
}
