package com.olejarczykjakub.vet_server.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.olejarczykjakub.vet_server.model.UserModel;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@PostMapping("/api/v1/create-user")
	public Map<String, String> createUser(@RequestBody UserModel userModel) {
		System.out.println("Received a new user data:");
		System.out.println("homeAccountId = " + userModel.getHomeAccountId());
		System.out.println("name = " + userModel.getName());
		System.out.println("username = " + userModel.getUsername());
		System.out.println();
		return Map.of("message", "A new user has been created!");
	}
}
