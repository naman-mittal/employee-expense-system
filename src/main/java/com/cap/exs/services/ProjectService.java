package com.cap.exs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cap.exs.entities.Project;
import com.cap.exs.repos.*;


@Service 
public interface ProjectService {

	public List<Project> getAllProject();
	
	public Project addProject(Project project);
	
//	public List<Project> getAllProjectCode(int Id);
	
	public Project updateProject(Project project);
	
	public Project deleteProjectById(int id);
	
//	public void deleteAllProject();
	
	public List<Integer> getAllProjectCodes();
	
	public Project findByCode(int projectCode);
}
