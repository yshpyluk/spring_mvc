package com.example.service;


import com.example.entity.User;

import java.util.List;

/**
 * Created by yshpyluk on 4/5/17.
 */
public interface UserService {

	List<User> getAllUsers();
	User get(Long id);
	void add(String name);
	void remove(Long id);
	User update(User user);
}
