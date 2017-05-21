package com.example.domain.github;

import com.example.domain.github.entity.dto.GitProjectDto;
import com.example.domain.github.entity.dto.GitUserDto;
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

	public List<GitUserDto> getUsers() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GitUserDto[]> responseEntity = restTemplate.getForEntity("https://api.github.com/users", GitUserDto[].class);
		List<GitUserDto> users = Arrays.asList(responseEntity.getBody());
		return users;
	}

	public GitUserDto getUser(String login) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GitUserDto> responseEntity = restTemplate.getForEntity("https://api.github.com/users/{login}", GitUserDto.class, login);
		GitUserDto user = responseEntity.getBody();
		return user;
	}

	public List<GitProjectDto> getUserRepos(String login) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GitProjectDto[]> responseEntity = restTemplate.getForEntity("https://api.github.com/users/{login}/repos", GitProjectDto[].class, login);
		return Arrays.asList(responseEntity.getBody());
	}
}
