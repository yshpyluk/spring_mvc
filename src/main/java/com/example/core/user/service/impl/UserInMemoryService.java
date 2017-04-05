package com.example.core.user.service.impl;

import com.example.core.general.GenericDAO;
import com.example.core.user.User;
import com.example.core.user.dao.UserInMemoryDAO;
import com.example.core.user.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yshpyluk on 4/5/17.
 */
@Service
//@Profile("inmemory")
public class UserInMemoryService implements UserService {
	private static int counter = 0;

	GenericDAO<User> users = new UserInMemoryDAO();

	@Override
	public List<User> getAllUsers() {
		return users.getAll();
	}

	@Override
	public User get(int i) {
		return users.get(i);
	}

	@Override
	public void add(String name) {
		users.add(new User(name, ++counter));
	}

	@Override
	public void remove(int id) {
		User userToDelete = users.getAll().stream().filter(u -> u.getId() == id).findFirst().get();
		users.remove(userToDelete);
	}

	@Override
	public void remove(String name) {
		User userToDelete = users.getAll().stream().filter(u -> u.getName().toLowerCase().equals(name.toLowerCase())).findFirst().get();
		users.remove(userToDelete);
	}

	@Override
	public User update(int id) {
		return null;
	}

	@Override
	public User update(String name) {
		return null;
	}
}
