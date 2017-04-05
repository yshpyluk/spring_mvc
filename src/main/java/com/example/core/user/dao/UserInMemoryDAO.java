package com.example.core.user.dao;

import com.example.core.general.GenericDAO;
import com.example.core.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yshpyluk on 4/5/17.
 */
public class UserInMemoryDAO implements GenericDAO<User> {
	private List<User> users;

	public UserInMemoryDAO(){
		users = new ArrayList<>();
	}

	@Override
	public List<User> getAll() {
		return users;
	}

	@Override
	public User get(int i) {
		return users.get(i);
	}

	@Override
	public void add(User entity) {
		users.add(entity);
	}

	@Override
	public void remove(User entity) {
		users = users.stream()
				.filter(u -> u.getId() != entity.getId())
				.collect(Collectors.toList());
	}

	@Override
	public User update(User entity) {
		return null;
	}
}
