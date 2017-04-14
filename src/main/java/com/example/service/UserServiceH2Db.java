package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yshpyluk on 4/14/17.
 */
@Service("h2_service")
public class UserServiceH2Db implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User get(Long id) {
		return null;
	}

	@Override
	public void add(String name) {

	}

	@Override
	public void remove(Long id) {

	}

	@Override
	public User update(Long id) {
		return null;
	}
}
