package com.cap.exs.repos;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseClaimRepository extends JpaRepository<ExpenseClaim, Integer> {
	public List<ExpenseClaim> getAllExpenseClaim();
	
	public ExpenseClaim addExpenseClaim(ExpenseClaim expenseClaim);
	
	public ExpenseClaim findExpenseClaimById(int expenseCodeID);
	
	public ExpenseClaim uppdateExpenseClaim(ExpenseClaim expenseClaim);
	
	public ExpenseClaim deleteExpenseClaimById(int id);
	
	public List<ExpenseClaim> getAllClaimsByEmployee(Employee emp);



}
