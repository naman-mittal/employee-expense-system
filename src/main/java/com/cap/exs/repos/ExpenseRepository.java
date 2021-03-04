package com.cap.exs.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.exs.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	
	// public Expense findByCode(int code);
	

}
