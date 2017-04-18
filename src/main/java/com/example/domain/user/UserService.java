package com.example.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by yshpyluk on 4/14/17.
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User get(Long id) {
		return userRepository.findOne(id);
	}

	public Optional<User> getByName(String name) {
		return userRepository.findByName(name);
	}

	public void add(User user) {
		userRepository.save(user);
	}

	public void remove(Long id) {
		userRepository.delete(id);
	}

	public User update(User user) {
		User updateEntity = userRepository.findOne(user.getId());
		updateEntity.setName(user.getName());

		userRepository.save(updateEntity);
		return userRepository.findOne(user.getId());
	}
}
