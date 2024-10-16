package com.umesh.jwtnotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umesh.jwtnotes.entity.User;
import com.umesh.jwtnotes.repository.UserRepo;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@GetMapping("/info")
	public User getUserDetails() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepo.findByEmail(email).get();
	}

}
