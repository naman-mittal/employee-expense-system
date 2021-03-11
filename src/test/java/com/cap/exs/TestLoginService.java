package com.cap.exs;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cap.exs.entities.LoginDetails;
import com.cap.exs.repos.ILoginRepository;
import com.cap.exs.services.LoginService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLoginService {
	
	@Autowired
	LoginService loginService;
	
	@MockBean
	ILoginRepository loginRepository;
	
	
	@Test
	public void testAddDetails() {
	LoginDetails ld =new LoginDetails();
	ld.setUserName("aman");
	ld.setPassword("aman");
	ld.setRole("analyst");

	when (loginRepository.save(ld)).thenReturn(ld);
	
	assertEquals(ld,loginService.addDetails(ld));
	
	
	
	}
	
}
