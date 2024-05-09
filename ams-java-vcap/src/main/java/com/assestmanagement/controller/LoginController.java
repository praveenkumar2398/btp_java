package com.assestmanagement.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assestmanagement.dto.ErrorResponseDto;
import com.assestmanagement.dto.LoginRequestData;
import com.assestmanagement.dto.LoginResponse;
import com.assestmanagement.exception.EmailAlreadyExistsException;
import com.assestmanagement.service.EmployeeService;
import com.assestmanagement.service.impl.EmployeeDetailsServiceImpl;
import com.assestmanagement.utils.JwtUtil;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
	
	@Autowired
	private EmployeeService employeeService;
	

    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status Success"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "HTTP Status Unauthorized",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
}
)
	@PostMapping
	public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequestData employeeData) throws IOException{
			LoginResponse loginResponse = employeeService.authenticateGenerateToken(employeeData);
	        return ResponseEntity.ok(loginResponse);
	}

}


















//public LoginResponse generateTokenResponse(LoginRequestData employeeData) {
//final UserDetails employeeDetails = employeeDetailsServiceImpl.loadUserByUsername(employeeData.getEmailId());
//final String jwt = jwtUtil.generateToken(employeeDetails.getUsername(),employeeDetails.getAuthorities());
//List<String> role = employeeDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//return new LoginResponse(employeeDetails.getUsername().toString(),role,jwt);
//
//}
