package com.cap.exs.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.exs.entities.Project; 

@Repository
public interface IProjectRepository extends JpaRepository<Project, Integer>{
	
	public List<Integer> getAllProjectCodes();
	
}
