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
		return userRepository.findOne(id);
	}

	@Override
	public void add(String name) {
		User user = new User(name);
		userRepository.save(user);
	}

	@Override
	public void remove(Long id) {
		userRepository.delete(id);
	}

	@Override
	public User update(User user) {
		User updateEntity = userRepository.findOne(user.getId());
		updateEntity.setName(user.getName());

		userRepository.save(updateEntity);
		return userRepository.findOne(user.getId());
	}
}
