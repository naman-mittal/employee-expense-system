package com.cap.exs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cap.exs.entities.Project;
import com.cap.exs.repos.*;
import com.cap.exs.service_interfaces.IProjectService;


@Service 
public class ProjectService implements IProjectService{

	public List<Project> getAllProject(){
		return null;
		
	}
	
	public Project addProject(Project project) {
		return project;
		
	}
	
//	public List<Project> getAllProjectCode(int Id){}
	
	public Project updateProject(Project project) {
		return project;
		
	}
	
	public Project deleteProjectById(int id) {
		return null;
		
	}
	
//	public void deleteAllProject(){}
	
	public List<Integer> getAllProjectCodes(){
		return null;
		
	}
	
	public Project findByCode(int projectCode) {
		return null;
		
	}
}
