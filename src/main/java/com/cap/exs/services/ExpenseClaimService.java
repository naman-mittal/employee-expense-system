package com.cap.exs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cap.exs.entities.Employee;
import com.cap.exs.repos.EmployeeRepository;
import com.cap.exs.repos.ExpenseClaim;

public class ExpenseClaimService {
	@Autowired
	ExpenseClaimRepository expenseClaimRepository;
	
	public ExpenseClaim addExpenseClaim(ExpenseClaim expenseClaim) {
		
		return expenseClaimRepository.save(ExpenseClaim);
	}
	public List<ExpenseClaim> getAllExpenseClaim(){
		
		List<ExpenseClaim> expenseClaim = expenseClaimRepository.findAll();
		
		if(expenseClaim.isEmpty())
		{
			//throw exception
		}
		
		return expenseClaim;
		
	}
	
	public ExpenseClaim findExpenseClaimById(int expenseCodeID) {
		
		return expenseClaimRepository.findById(expenseCodeID);
	
	}
	
	public ExpenseClaim uppdateExpenseClaim(ExpenseClaim expenseClaim) {
		
		return null;
		
	}
	
	public ExpenseClaim deleteExpenseClaimById(int id) {
		
		ExpenseClaim expenseClaim = this.findById(id);
		
		expenseClaimRepository.delete(expenseClaim);
		
	}
	
	public List<ExpenseClaim> getAllClaimsByEmployee(Employee emp){
		//LOGIC
		
		return null;
	}


	
}
