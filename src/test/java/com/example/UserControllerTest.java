package com.example;

import com.example.domain.user.UserDto;
import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.org.apache.regexp.internal.RESyntaxException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.soap.SOAPBinding;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by yshpyluk on 4/15/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	@After
	public void tearDown() {
		userRepository.deleteAll();
	}

	@Test
	public void testAddUser() throws Exception {
		// Given
		String requestBody = asJsonString(new UserDto("TEST_USER"));

		// When
		mockMvc.perform(post("/group/user/add")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print());

		// Then
		assertEquals(1, userRepository.count());
		//TODO add assert of user field set like were in UserDro posted
	}

	@Test
	public void testAddUser_conflict() throws Exception {
		// Given
		User existingUser = User.builder().name("TEST_USER").build();
		userRepository.save(existingUser);
		String requestBody = asJsonString(existingUser.convertToDto());

		// When
		ResultActions actions = mockMvc.perform(post("/group/user/add")
				.content(requestBody).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print());

		// Then
		assertEquals(1, userRepository.count());
		actions.andExpect(status().isConflict());
	}

	// 	Example of to use assertion
	//		actions.andExpect(jsonPath("$.child.elem.emem.fiel2", CoreMatchers.is("TEST_USER")));

	@Test
	public void testAddUser_validationFails() throws Exception {
		//Given
		User user = User.builder().name(null).build();
		String requestBody = asJsonString(user);

		//When
		ResultActions resultActions = mockMvc.perform(post("/group/user/add")
				.content(requestBody).contentType(MediaType.APPLICATION_JSON));

		//Than
		assertEquals(0, userRepository.count());
		resultActions.andExpect(status().isBadRequest());
	}

	@Test
	public void testGetUser() throws Exception {
		//Given
		User user = User.builder().name("First").build();
		userRepository.save(user);

		//When
		ResultActions resultActions = mockMvc.perform(get("/group/user/{id}", user.getId()));
		String content = resultActions.andReturn().getResponse().getContentAsString();
		User actualUser = objectMapper.readValue(content, User.class);

		//Than
		assertEquals(user, actualUser);
	}

	@Test
	public void testGetUser_negative() throws Exception {
		//When
		ResultActions resultActions = mockMvc.perform(get("/group/user/{id}", "dummyId"));

		//Than
		resultActions.andExpect(status().isConflict());
	}

	@Test
	public void testUpdateUser() throws Exception {
		//Given
		User user = new UserDto("Test User").convert();
		userRepository.save(user);
		User userToUpdate = userRepository.findByName(user.getName()).get();
		userToUpdate.setName("Another name");

		//When
		mockMvc.perform(put("/group/user/update")
				.content(asJsonString(userToUpdate)).contentType(MediaType.APPLICATION_JSON));

		//Than
		assertEquals(1, userRepository.count());

		User actualUser = userRepository.findOne(userToUpdate.getId());
		assertEquals(userToUpdate.getName(), actualUser.getName());
	}

	@Test
	public void testUpdateUser_negative() throws Exception {
		//Given
		User user = new UserDto("Test User").convert();
		user.setId(1L);
		String body = asJsonString(user);

		//When
		mockMvc.perform(put("/group/user/update")
				.content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		//Than
		assertEquals(0, userRepository.count());
	}

	@Test
	public void testDeleteUser() throws Exception {
		//Given
		userRepository.save(new UserDto("first").convert());
		userRepository.save(new UserDto("second").convert());
		userRepository.save(new UserDto("third").convert());
		User user = userRepository.findAll().get(1);

		//When
		ResultActions resultActions = mockMvc.perform(delete("/group/user/{id}", user.getId()))
				.andExpect(status().isAccepted());

		//Than
		assertEquals(2, userRepository.count());
	}

	public String asJsonString(Object o) throws Exception {
		return objectMapper.writeValueAsString(o);
	}
}