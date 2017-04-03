package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yshpyluk on 4/2/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping(method = RequestMethod.GET)
	public String getMessage(ModelMap modelMap) {
		modelMap.addAttribute("message", "Hello app world");
		return "user";
	}
}
