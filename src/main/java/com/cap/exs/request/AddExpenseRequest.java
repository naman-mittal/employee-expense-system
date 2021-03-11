package com.cap.exs.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddExpenseRequest {
	
	
	@NotNull
	@Size(min = 5,max = 30)
	private String expenseType;
	
	@NotNull
	@Size(min = 5,max = 50)
	private String expenseDescription;

	public AddExpenseRequest() {
		super();
		
	}

	public AddExpenseRequest(@NotNull @Size(min = 5, max = 30) String expenseType,
			@NotNull @Size(min = 5, max = 50) String expenseDescription) {
		super();
		this.expenseType = expenseType;
		this.expenseDescription = expenseDescription;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	
	

}
