package com.cap.exs.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.exs.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	
	public Employee findByLoginDetails(LoginDetails loginDetails);
	
}
