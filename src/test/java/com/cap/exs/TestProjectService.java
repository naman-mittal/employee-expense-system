package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.*;
import com.cap.exs.repos.*;
import com.cap.exs.services.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProjectService {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	IProjectRepository projectRepository;
	
	@Autowired
	ExpenseClaimService expenseClaimService;
	
	
	@Test
	public void getAllProject(){
		
	}
	
	
	@Test
	public void addProject() {
		
	}
	
	
	@Test
	public void updateProject() {
		
	}
	
	
	@Test
	public void deleteProjectById() {
		projectService.deleteProjectById(1);
		assertEquals((int) 1,(int) projectRepository.count());
	}
	
	
	@Test
	public void getAllProjectCodes(){
		List<Integer> allProjectCodes = projectService.getAllProjectCodes();
		assertEquals(2, allProjectCodes.size());	
	}
	
	
	@Test
	public void findByCode() {
		Project project = projectService.findByCode(1);
		assertNotNull(project);
	}

}
