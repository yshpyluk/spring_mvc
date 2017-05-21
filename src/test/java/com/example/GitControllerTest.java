package com.example;

import com.example.domain.github.GitController;
import com.example.domain.github.GitUserRepository;
import com.example.domain.github.entity.dto.GitUserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by yshpyluk on 5/21/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GitControllerTest {

	@Autowired
	private GitController gitController;
	@Autowired
	private GitUserRepository userRepository;

	@Test
	public void userIsPresent() {
		assertEquals(0, userRepository.findAll().size());

		String expectedName = "yshpyluk";
		GitUserDto expectedUser = GitUserDto.builder().login(expectedName).build();
		GitUserDto controllerResponseUser = gitController.getUser(expectedName);
		assertEquals(expectedUser.getLogin(), controllerResponseUser.getLogin());
		assertEquals(1, userRepository.findAll().size());
	}
}
