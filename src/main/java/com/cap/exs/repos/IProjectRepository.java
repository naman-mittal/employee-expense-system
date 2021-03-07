package com.cap.exs.repos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cap.exs.entities.*; 

@Repository
public interface IProjectRepository {

	public List<Project> getAllProject();
	
	public Project addProject(Project project);
	
	public List<Project> getAllProjectCode(int Id);
	
	public Project updateProject(Project project);
	
	public void deleteProjectById(int id);
	
	public void deleteAllProject();
	
	public List<Integer> getAllProjectCodes();
	
	public Project findByCode(int projectCode);
}
