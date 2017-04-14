package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yshpyluk on 4/2/17.
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	@Qualifier("in_memory_service")
	UserService userService;

//	@Autowired
//	public UserController(UserService userService){
//		this.userService = userService;
//	}

	@RequestMapping(method = RequestMethod.GET)
	public String getMessage(
			ModelMap modelMap) {
		modelMap.addAttribute("users", userService.getAllUsers());
		return "users";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(
			@RequestParam("name")
			String name,
			ModelMap modelMap) {
		userService.add(name);
		modelMap.addAttribute("Success_message",
				String.format("User %1s was successfully added", name));
		return "success";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removeUser(
			@RequestParam("id")
			Long id,
			ModelMap modelMap) {
		userService.remove(id);
		modelMap.addAttribute("Success_message",
				String.format("User %1s was successfully removed", id));
		return "success";
	}
}
