package com.example;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

	@Before
	public void qqq() {
		userRepository.save(new User("Jack"));
		userRepository.save(new User("John"));
		userRepository.save(new User("Roger"));
		userRepository.save(new User("Mari"));
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

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/group/user/Jakob"))
				.andExpect(status().isOk())
				.andReturn();

		Assert.assertTrue(result.getResponse().getContentAsString().contains("John was added"));
		Assert.assertEquals( "Expected that number of users increased by 1 after adding new User",
				initialCount + 1,
				userRepository.findAll().size());

		User user = userRepository.findAll().stream()
				.filter(u -> u.getName().equals("Jakob"))
				.findFirst()
				.get();
		Assert.assertNotNull("Expected that user was added after add operation", user);
	}

	@After
	public void tearDown() {
		userRepository.flush();
	}
}
