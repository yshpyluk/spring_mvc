package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yshpyluk on 4/15/17.
 */
@RestController
@RequestMapping("/group")
public class GroupController {

	@Autowired
	@Qualifier("h2_service")
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getGroupWelcome() {
		return "Welcome! You are on Group page!!!";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/update")
	public String updateUser(@RequestBody User user) {
		User updatedUser = userService.update(user);
		return String.format("User %s was updated", updatedUser.getName());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/{userName}")
	public String addUser(@PathVariable String userName) {
		userService.add(userName);
		return String.format("User %s was added", userName);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}")
	public String removeUser(@PathVariable Long userId) {
		User user = userService.get(userId);
		userService.remove(userId);
		return String.format("User %s was deleted", user.getName());
	}
}
