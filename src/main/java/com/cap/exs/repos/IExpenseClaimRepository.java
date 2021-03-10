package com.cap.exs.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.Expense;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.Project;


public interface IExpenseClaimRepository extends JpaRepository<ExpenseClaim, Integer> {
	
	public List<ExpenseClaim> findByEmployee(Employee employee);
	
	public List<ExpenseClaim> findByProject(Project project);
	
	public List<ExpenseClaim> findByExpense(Expense expense);

}
