package com.cap.exs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Project;
import com.cap.exs.services.ProjectService;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	@PostMapping("/project")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Project> getAllProject(){
		return projectService.getAllProject();
	}
	
	
	@GetMapping("/project")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Project addProject(@RequestBody Project project) {
		return projectService.addProject(project);
	}
	
	
//	public List<Project> getAllProjectCode(int Id){}
	
	@GetMapping("/project")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Project updateProject(@RequestBody Project project) {
		return projectService.updateProject(project);
	}
	
	
	@GetMapping("/project/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Project deleteProjectById(@PathVariable("id") int id) {
		return projectService.deleteProjectById(id);
	}
	
	
//	public void deleteAllProject(){}
	
	@GetMapping("/project/{id}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Integer> getAllProjectCodes(){
		return projectService.getAllProjectCodes();		
	}
	
	
	@GetMapping("/project/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Project findByCode(@PathVariable("id") int projectCode) {
		return projectService.findByCode(projectCode);	
	}
}