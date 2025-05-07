package com.olejarczykjakub.vet_server.controller;

import com.olejarczykjakub.vet_server.model.Users;
import com.olejarczykjakub.vet_server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
	private final UsersRepository usersRepository;

	@PostMapping("/api/v1/create-user")
	public Map<String, String> createUser(@RequestBody Users users) {
		val homeAccountId = users.getHomeAccountId();
		val userByHomeAccountId = usersRepository.findByHomeAccountId(homeAccountId);
		if (userByHomeAccountId.isEmpty()) {
			usersRepository.save(users);
			return Map.of("message", "You are a new user!");
		}
		return Map.of("message", "Welcome back!");
	}
}
