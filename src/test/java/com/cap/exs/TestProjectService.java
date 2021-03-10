package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
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
	public void testGetAllProject(){
		Project project1 = new Project(11, "Testing", LocalDate.of(2021, 01, 11), LocalDate.of(2021, 06, 11));
		Project project2 = new Project(12, "Full-Stack", LocalDate.of(2021, 01, 12), LocalDate.of(2021, 06, 12));
		
		when(projectRepository.findAll()).thenReturn(Arrays.asList(project1,project2));
		assertEquals(2, projectService.getAllProject().size());	
	}
	
	
	@Test
	public void testAddProject() {
		Project project = new Project();
		project.setProjectCode(1);
		project.setProjectDescription("Java");
		
		LocalDate startDate = LocalDate.of(2021, 03, 01);
		project.setStartDate(startDate);
		
		LocalDate endDate = LocalDate.of(2021, 06, 01);
		project.setEndDate(endDate);
		
//		Project project = new Project(10, "XYZ", LocalDate.of(2021, 03, 01), LocalDate.of(2021, 06, 20));
		
		when(projectRepository.save(project)).thenReturn(project);
//		assertEquals(project, projectService.addProject(project));	
	}
	
	
	@Test
	public void testUpdateProject() {
		
	}
	
	
	@Test
	public void testDeleteProjectById() {
		projectService.deleteProjectById(1);
		assertEquals((int) 1,(int) projectRepository.count());
	}
	
	
	@Test
	public void testGetAllProjectCodes(){
		List<Integer> allProjectCodes = projectService.getAllProjectCodes();
		assertEquals(2, allProjectCodes.size());	
	}
	
	
	@Test
	public void testFindByCode() {
		Project project = projectService.findByCode(1);
		assertNotNull(project);
	}

}
