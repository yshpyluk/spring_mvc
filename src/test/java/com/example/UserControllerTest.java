package com.example;

import com.example.domain.user.UserDto;
import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by yshpyluk on 4/15/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

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
				.contentType(MediaType.TEXT_HTML))
				.andDo(print());

		// Then
		assertEquals(1, userRepository.count());
	}

	@Test
	public void testAddUser_conflict() throws Exception {
		// Given
		User exisitingUser = User.builder().name("TEST_USER").build();
		userRepository.save(exisitingUser);
		String requestBody = asJsonString(exisitingUser);

		// When
		ResultActions actions = mockMvc.perform(post("/group/user/add")
				.content(requestBody).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print());

		// Then
		assertEquals(1, userRepository.count());
		actions.andExpect(status().isConflict());

		actions.andExpect(jsonPath("$.child.elem.emem.fiel2", CoreMatchers.is("TEST_USER")));

	}

	@Test
	public void testUpdateUser() throws Exception {
		// Given
		User exisitingUser = User.builder().name("TEST_USER").build();
		String requestBody = asJsonString(exisitingUser);

		// When
		mockMvc.perform(put("/group/user/update")
				.content(requestBody).contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print());

		// Then
		assertEquals(1, userRepository.count());
	}


	@Test
	public void testGroupEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/group"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Welcome! You are on Group page!!!")));
	}

	@Test
	public void testAddUserEndpoint() throws Exception {
		int initialCount = userRepository.findAll().size();

		MvcResult result = mockMvc.perform(post("/group/user/Jakob"))
				.andExpect(status().isOk())
				.andReturn();

		Assert.assertTrue(result.getResponse().getContentAsString().contains("John was added"));
		assertEquals( "Expected that number of users increased by 1 after adding new User",
				initialCount + 1,
				userRepository.findAll().size());

		User user = userRepository.findAll().stream()
				.filter(u -> u.getName().equals("Jakob"))
				.findFirst()
				.get();
		Assert.assertNotNull("Expected that user was added after add operation", user);
	}

	public String asJsonString(Object o) throws Exception {
		return objectMapper.writeValueAsString(o);
	}
}
