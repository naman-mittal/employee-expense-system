package com.cap.exs.repos;

import org.springframework.stereotype.Repository;

import com.cap.exs.entities.*; 

@Repository
public interface IProjectRepository {
	
	public Project findByCode(Project project);
	
}
