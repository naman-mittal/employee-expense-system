package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.exs.entities.Project;
import com.cap.exs.exceptions.ExpenseClaimAssociatedException;
import com.cap.exs.exceptions.ProjectNotFoundException;
import com.cap.exs.repos.IProjectRepository;
import com.cap.exs.service_interfaces.IProjectService;


@Service 
public class ProjectService implements IProjectService{
	
	@Autowired
	IProjectRepository projectRepository;
	
	Logger logger = LoggerFactory.getLogger(ProjectService.class);
	
	//change this....DONE
	public List<Project> getAllProject(){
		List<Project> projects = projectRepository.findAll();
		
		if(projects.isEmpty()) {
			String errorMessage = "No Projects Found";
			logger.error(errorMessage, ProjectNotFoundException.class);
			throw new ProjectNotFoundException(errorMessage);
		}
		
		return projects;
	}
	
	
	public Project addProject(Project project) {
		
		return projectRepository.save(project);
	}
	

	
	public Project updateProject(Project project) {
		
		this.findByCode(project.getProjectCode());
		
		return projectRepository.save(project);
	}
	
	//change this....
	public Project deleteProjectById(int id) {

		Project project = this.findByCode(id);
		try {
			projectRepository.delete(project);
		}
		catch(DataIntegrityViolationException  e) {
			String errorMessage = String.format("expense claim exist for project = %s", project.toString());
			logger.error(errorMessage, ExpenseClaimAssociatedException.class);
			throw new ExpenseClaimAssociatedException(errorMessage);
		}
		return project;
	}
	
	
//	public void deleteAllProject(){}
	
	
	public List<Integer> getAllProjectCodes(){
		List<Integer> projectCodes = projectRepository.getAllProjectCodes();
		
		if(projectCodes.isEmpty()) {
			String errorMessage = "No projects found...";
			logger.error(errorMessage, ProjectNotFoundException.class);
			throw new ProjectNotFoundException(errorMessage);
		}
		
		return projectCodes;
	}
	
	
	public Project findByCode(int projectCode) {
		Optional<Project> project = projectRepository.findById(projectCode);
		if(!project.isPresent())
		{
			String errorMessage = String.format("no project found with id = %d", projectCode);
			logger.error(errorMessage, ProjectNotFoundException.class);
			throw new ProjectNotFoundException(errorMessage);
		}
		return project.get();		
	}
}
