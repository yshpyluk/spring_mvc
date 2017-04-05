package com.example.core.user.service;


import com.example.core.user.User;

import java.util.List;

/**
 * Created by yshpyluk on 4/5/17.
 */
public interface UserService {

	List<User> getAllUsers();
	User get(int i);
	void add(String name);
	void remove(int id);
	void remove(String name);
	User update(int id);
	User update(String name);
}
