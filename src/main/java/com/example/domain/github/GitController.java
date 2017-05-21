package com.example.domain.github;

import com.example.domain.github.entity.GitProject;
import com.example.domain.github.entity.GitUser;
import com.example.domain.github.entity.dto.GitProjectDto;
import com.example.domain.github.entity.dto.GitUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by yshpyluk on 5/9/17.
 */
@RestController
@RequestMapping("/github")
public class GitController {

	@Autowired
	private GitUserRepository userRepository;

	@Autowired
	protected GitProjectRepository projectRepository;

	@Autowired
	private GitClient gitClient;

	@GetMapping(value = "/users")
	public List<GitUserDto> getUsers() {
		List<GitUserDto> usersDto;
		List<GitUser> users = userRepository.findAll();
		if (users.size() == 0) {
			usersDto = gitClient.getUsers();
			usersDto.forEach(u -> users.add(u.toEntity()));
			userRepository.save(users);
		} else {
			usersDto = new ArrayList<>();
			users.forEach(u -> usersDto.add(u.toDto()));
		}

		return usersDto;
	}

	@GetMapping(value = "/users/{username}")
	public GitUserDto getUser(@PathVariable("username") String login) {
		Optional<GitUser> user = userRepository.findByLogin(login);
		if (!user.isPresent()) {
			GitUserDto userDto = gitClient.getUser(login);
			user = Optional.of(userRepository.save(userDto.toEntity()));
		}
		return user.get().toDto();
	}

	@GetMapping(value = "/user/{username}")
	public List<GitProjectDto> getUserRepos(@PathVariable("username") String login) {
		List<GitProjectDto> projectsDto;
		List<GitProject> projects = projectRepository.findByOwnerLogin(login);
		if (projects.size() == 0) {
			projectsDto = gitClient.getUserRepos(login);
			projectsDto.forEach(p -> projects.add(p.toEntity()));
			projectRepository.save(projects);
		} else {
			projectsDto = new ArrayList<>();
			projects.forEach(p -> projectsDto.add(p.toDto()));
		}
		return projectsDto;
	}
}

