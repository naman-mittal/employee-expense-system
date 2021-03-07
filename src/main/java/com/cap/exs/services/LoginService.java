package com.cap.exs.services;

import com.cap.exs.entities.LoginDetails;
import com.cap.exs.service_interfaces.ILoginService;

public class LoginService implements ILoginService{

public LoginDetails addDetails(LoginDetails details) {
		return details;
	}
	
public void deleteDetailsById(int id) {
	
}


public LoginDetails validateUser(LoginDetails details) {
	return details;
}


//public LoginDetails logout(LoginDetails details) {
//	return details;
//}

}
