package com.olejarczykjakub.vet_server.controller;

import com.olejarczykjakub.vet_server.model.Users;
import com.olejarczykjakub.vet_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/create-user")
	public Map<String, String> createUser(@RequestBody Users users) {
		if (userService.existUser(users)) {
			return Map.of("message", "Welcome back!");
		}
		userService.createUser(users);
		return Map.of("message", "You are a new user!");
	}
}
