package com.howtodoinjava.app.services.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.app.repositories.user.UserRepository;
import com.howtodoinjava.app.services.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;

	@Override
	public Integer getUserCount() {
		return (int) repository.count();

	}

}
