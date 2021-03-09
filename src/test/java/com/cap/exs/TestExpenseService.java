package com.cap.exs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.repos.IExpenseRepository;
import com.cap.exs.services.ExpenseService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestExpenseService {
	
	@Autowired
	IExpenseRepository expenseRepository;
	
	
	@Autowired
	ExpenseService expenseService;
	
	@Test
	public void testAddExpense()
	{
		// 
	}

}
