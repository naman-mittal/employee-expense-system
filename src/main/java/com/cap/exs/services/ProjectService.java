package com.cap.exs.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cap.exs.controllers.LoggingController;
import com.cap.exs.entities.Project;
import com.cap.exs.repos.IProjectRepository;
import com.cap.exs.service_interfaces.IProjectService;
import com.cap.exs.exceptions.ProjectAlreadyExistException;
import com.cap.exs.exceptions.ProjectNotFoundException;


@Service 
public class ProjectService implements IProjectService{
	
	@Autowired
	IProjectRepository projectRepository;
	
	Logger logger = LoggerFactory.getLogger(LoggingController.class);
	
	//change this....DONE
	public List<Project> getAllProject(){
		List<Project> projects = projectRepository.findAll();
		
		if(projects.isEmpty()) {
			logger.error("No Projects Found", ProjectNotFoundException.class);
			throw new ProjectNotFoundException("No projects found...");
		}
		
		return projects;
	}
	
	
	public Project addProject(Project project) {
		List<Integer> allProjects = this.getAllProjectCodes();
		if(allProjects.contains((Object)project)) {
			logger.error("Project with this code already exists", ProjectAlreadyExistException.class);
			throw new ProjectAlreadyExistException("Project with this code already exists...");
		}
		
		return projectRepository.save(project);
	}
	
//	public List<Project> getAllProjectCode(int Id){}
	
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}
	
	//change this....DONE
	public Project deleteProjectById(int id) {
//		Project project = projectRepository.findById(id).get();
		Project project = this.findByCode(id);
		try {
			projectRepository.delete(project);
		}
		catch(DataIntegrityViolationException  e) {
			logger.error("No such project found", ProjectNotFoundException.class);
			throw new ProjectNotFoundException("No such project exist with given Id: " + project);
		}
		return project;
	}
	
	
//	public void deleteAllProject(){}
	
	
	public List<Integer> getAllProjectCodes(){
		List<Integer> projectCodes = projectRepository.getAllProjectCodes();
		
		if(projectCodes.isEmpty()) {
			logger.error("No Project Code Found", ProjectNotFoundException.class);
			throw new ProjectNotFoundException("No project code found...");
		}
		
		return projectCodes;
	}
	
	
	public Project findByCode(int projectCode) {
		Optional<Project> project = projectRepository.findById(projectCode);
		if(!project.isPresent())
		{
			logger.error("No Projects Found", ProjectNotFoundException.class);
			throw new ProjectNotFoundException("No project found with projectCode: " + projectCode);
		}
		return project.get();		
	}
}
