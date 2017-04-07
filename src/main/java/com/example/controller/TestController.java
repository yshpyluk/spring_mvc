package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yshpyluk on 4/6/17.
 */
@RestController
@RequestMapping("/")
public class TestController {

	@RequestMapping(method = RequestMethod.GET)
	public String getTest() {
		return "Hey, you are done!!! \n";
	}
}
