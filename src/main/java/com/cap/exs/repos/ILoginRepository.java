package com.cap.exs.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cap.exs.entities.LoginDetails;


public interface ILoginRepository extends JpaRepository<LoginDetails,Integer>{
	LoginDetails findByUserName(String userName);

}
	
	
	

	
