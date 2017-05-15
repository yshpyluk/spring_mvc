package com.example.domain.github;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yshpyluk on 5/14/17.
 */
@Component
public class GitClient {

	private RestTemplate restTemplate;

	public GitClient() {
		restTemplate = new RestTemplate();
	}

	public List<User> getUsers() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("https://api.github.com/users", User[].class);
		List<User> users = Arrays.asList(responseEntity.getBody());
		return users;
	}

	public List<Project> getUserRepos(String login) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Project[]> responseEntity = restTemplate.getForEntity("https://api.github.com/users/{login}/repos", Project[].class, login);
		return Arrays.asList(responseEntity.getBody());
	}

}
