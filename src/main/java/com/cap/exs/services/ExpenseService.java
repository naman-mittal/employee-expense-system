package com.cap.exs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Expense;
import com.cap.exs.repos.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	ExpenseRepository expenseRepository;

		public List<Integer> getAllExpenseCode()
		{
			List<Integer> expensesCodes = new ArrayList<Integer>();
			expensesCodes = expenseRepository.findAll().stream().map(e->e.getExpenseCode()).collect(Collectors.toList());
			return expensesCodes;
		}
		
		public Expense addExpense(Expense expense)
		{
			return expenseRepository.save(expense);
		}
		
		
		public List<Expense> getAllExpenses()
		{
			List<Expense> allExpenses = new ArrayList<Expense>();
			expenseRepository.findAll().forEach(e->allExpenses.add(e));
			return allExpenses;
		}
		
		public Expense getExpenseByCode(int id)
		{
			return expenseRepository.findById(id).get();
		}
		
		public Expense updateExpense(Expense expense)
		{
			return expenseRepository.save(expense);
		}
		
		public Expense deleteExpenseByCode(int expenseCode)
		{
			Expense expense = expenseRepository.findById(expenseCode).get();
			expenseRepository.delete(expense);
			return expense;
		}
		
		public void deleteAllExpenses()
		{
			// logic
		}
		
		public Expense findByCode(int expensecode)
		{
			Expense expense = expenseRepository.findById(expensecode).get();
			return expense;
		}
}
