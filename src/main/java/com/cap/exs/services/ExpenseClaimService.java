package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.ExpenseClaimNotFoundException;
import com.cap.exs.repos.ExpenseClaimRepository;

@Service
public class ExpenseClaimService {
	
	@Autowired
	ExpenseClaimRepository expenseClaimRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ProjectService projectSevice;
	
	@Autowired
	ExpenseService expenseService;
	
	public ExpenseClaim addExpenseClaim(ExpenseClaim expenseClaim) {
		
		Employee employee = employeeService.findByEmployeeCode(expenseClaim.getEmployee().getEmpId());
		Project project = projectService.findByCode(expenseClaim.getProject().getProjectCode());
		Expense expense = expenseService.findByCode(expenseClaim.getExpense().getExpenseCode());
		
		
		expenseClaim.setEmployee(employee);
		expenseClaim.setExpense(expense);
		expenseClaim.setProject(project);
		
		return expenseClaimRepository.save(expenseClaim);
	}
	public List<ExpenseClaim> getAllExpenseClaim(){
		
		List<ExpenseClaim> expenseClaims = expenseClaimRepository.findAll();
		
		if(expenseClaims.isEmpty())
		{
			throw new ExpenseClaimNotFoundException("no Claims found!!");
		}
		
		return expenseClaims;
		
	}
	
	public ExpenseClaim findExpenseClaimById(int expenseCodeID) {
		
		Optional<ExpenseClaim> expenseClaim = expenseClaimRepository.findById(expenseCodeID);
		if(!expenseClaim.isPresent())
		{
			throw new ExpenseClaimNotFoundException("no Claims found!!");
		}
		
		return expenseClaim.get();
	
	}
	
	public ExpenseClaim uppdateExpenseClaim(ExpenseClaim expenseClaim) {
		
		return null;
		
	}
	
	public ExpenseClaim deleteExpenseClaimById(int id) {
		
		ExpenseClaim expenseClaim = this.findExpenseClaimById(id);
		
		expenseClaimRepository.delete(expenseClaim);
		
		return expenseClaim;
		
	}
	
	public List<ExpenseClaim> getAllClaimsByEmployee(Employee employee){
		
		Employee foundEmployee = employeeService.findByEmployeeCode(employee.getEmpId());
		
		List<ExpenseClaim> expenseClaims = expenseClaimRepository.findByEmployee(foundEmployee);		
		if(expenseClaims.isEmpty())
		{
			throw new ExpenseClaimNotFoundException("no Expense claim  found with username = \" + username ");
		}
		
		return expenseClaims;
	}


	
}
