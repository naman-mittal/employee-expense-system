package com.cap.exs.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cap.exs.exceptions.EmployeeAssociatedException;
import com.cap.exs.exceptions.EmployeeNotFoundException;
import com.cap.exs.exceptions.ExpenseClaimAssociatedException;
import com.cap.exs.exceptions.InvalidUserException;
import com.cap.exs.exceptions.ProjectNotFoundException;
import com.cap.exs.exceptions.UsernameAlreadyExistException;

@RestControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(ConstraintViolationException.class)
	  ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	  ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		List<String> errors = e.getFieldErrors().stream().map((err)-> err.getField() + " : " +  err.getDefaultMessage()).collect(Collectors.toList());
	    return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
	  }
//	
	@ExceptionHandler(EmployeeNotFoundException.class)
	  ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(UsernameAlreadyExistException.class)
	  ResponseEntity<String> handleUsernameAlreadyExistException(UsernameAlreadyExistException e) {
	    return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	  }
	@ExceptionHandler(ProjectNotFoundException.class)
	ResponseEntity<String> handleProjectNotFoundException(ProjectNotFoundException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ExpenseClaimAssociatedException.class)
	ResponseEntity<String> handleExpenseClaimAssociatedException(ExpenseClaimAssociatedException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(EmployeeAssociatedException.class)
	ResponseEntity<String> handleEmployeeAssociatedException(EmployeeAssociatedException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(InvalidUserException.class)
	ResponseEntity<String> handleInvalidUserException(InvalidUserException e)
	{
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
}
