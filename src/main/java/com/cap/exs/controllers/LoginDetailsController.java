package com.cap.exs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cap.exs.entities.LoginDetails;
import com.cap.exs.services.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Validated
@RequestMapping("/api/v1")
@Api(value = "LoginDetails", tags = { "LoginAPI" })
public class LoginDetailsController {

	@Autowired
	LoginService loginService;
	
	
	
	
	// login 
	
	@GetMapping("/signin")
	@ApiOperation(value = "Signin", response = ResponseEntity.class)
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully signed in"),
            @ApiResponse(code = 400, message = "Check your input parameters"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
	public LoginDetails signIn(@ApiParam(name="Signin Request", required = true)@RequestBody LoginDetails details)
	{
		return loginService.validateUser(details);
	}
	
}
