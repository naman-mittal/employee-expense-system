package com.cap.exs.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.Project;
import com.cap.exs.request.AddProjectRequest;
import com.cap.exs.request.UpdateProjectRequest;
import com.cap.exs.services.ProjectService;

import io.swagger.annotations.Api;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "Project", tags = { "ProjectAPI" })
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	@GetMapping("/projects")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Project> getAllProject(){		//We will get all the projects available
		return projectService.getAllProject();
	}
	
	
	@PostMapping("/project")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Project addProject(@Valid @RequestBody AddProjectRequest request) {		//Add projects
		
		Project project = new Project();
		project.setProjectDescription(request.getDescription());
		project.setStartDate(request.getStartDate());
		project.setEndDate(request.getEndDate());
		
		return projectService.addProject(project);
	}
	
	@PutMapping("/project")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Project updateProject(@Valid @RequestBody UpdateProjectRequest request) {	//Update projects
		
		Project project = new Project();
		
		project.setProjectCode(request.getId());
		project.setProjectDescription(request.getDescription());
		project.setStartDate(request.getStartDate());
		project.setEndDate(request.getEndDate());
		
		return projectService.updateProject(project);
	}
	
	
	@DeleteMapping("/project/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Project deleteProjectById(@PathVariable("id")@Positive int id) {		//Delete projects by particular ID
		return projectService.deleteProjectById(id);
	}
	
	@DeleteMapping("/projects")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAllProject(){					//Extra method added to delete all projects at once
		projectService.deleteAllProject();
	}
	
	@GetMapping("/projects/projectCode")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Integer> getAllProjectCodes(){		//We will get all project codes(ID)
		return projectService.getAllProjectCodes();		
	}
	
	
	@GetMapping("/project/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Project findByCode(@PathVariable("id")@Positive int projectCode) {	//We will get the project by ID
		return projectService.findByCode(projectCode);	
	}
}
