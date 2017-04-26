package com.example.domain.user;

import com.example.domain.user.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

	public void add(UserDto userDto) {
		userRepository.save(userDto.convert());
	}

	public void remove(Long id) throws NoSuchUserException {
		User user = get(id);
		if (user == null) {
			throw new NoSuchUserException(user.getId());
		}
		userRepository.delete(id);
	}

	public User update(Long id, UserDto userDto) throws NoSuchUserException {
		User updateEntity = get(id);
		if (updateEntity == null) {
			throw new NoSuchUserException(id);
		}

		updateEntity.setName(userDto.getName());
		userRepository.save(updateEntity);
		return get(id);
	}
}
