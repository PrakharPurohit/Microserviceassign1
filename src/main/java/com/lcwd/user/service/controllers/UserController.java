package com.lcwd.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return user1;
	}
	
	// can only authorise user 
	@GetMapping("/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public User getUser(@PathVariable("userId") Integer id){
		User user = userService.getUser(id);
		return user;
	}
	
	// can only authorise admin
	@GetMapping
	@Scheduled(cron = "0 0 8 * * *")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> fetchAllUser(){
		List<User> users = userService.getUsers();
		return users;
	}
	
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable("userId") Integer id , @RequestBody User user) {
			return userService.deleteUser(id);
		}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable("userId") Integer id , @RequestBody User user) {
		return userService.updateUser(id, user);
	}
	}

