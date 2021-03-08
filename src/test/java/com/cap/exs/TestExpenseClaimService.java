package com.cap.exs;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.repos.IExpenseClaimRepository;
import com.cap.exs.services.ExpenseClaimService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestExpenseClaimService {
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	@Autowired
	IExpenseClaimRepository expenseClaimRepository;

	@Test
	public void testdeleteExpenseClaimById() {
		expenseClaimService.deleteExpenseClaimById(1);
		assertEquals(1, expenseClaimRepository.count());
	}

}
