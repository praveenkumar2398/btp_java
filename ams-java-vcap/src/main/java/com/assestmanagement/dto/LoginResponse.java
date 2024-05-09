package com.assestmanagement.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public record LoginResponse(String email,List<String> role,String jwt) {
	
}
