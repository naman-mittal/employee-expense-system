package com.cap.exs.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.ExpenseClaim;


public interface ExpenseClaimRepository extends JpaRepository<ExpenseClaim, Integer> {
	
	public Employee findByEmployee(Employee employee);

}
