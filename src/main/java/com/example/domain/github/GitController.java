package com.example.domain.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yshpyluk on 5/9/17.
 */
@RestController
@RequestMapping("/github")
public class GitController {

	@Autowired
	private RepositoryUser repositoryUser;

	@Autowired
	protected RepositoryProject repositoryProject;

	@Autowired
	private GitClient gitClient;

	@GetMapping(value = "/users")
	public List<User> getUsers() {
		List<User> users = repositoryUser.findAll();
		if (users.size() == 0) {
			users = gitClient.getUsers();
			repositoryUser.save(users);
		}
		return users;
	}

	@GetMapping(value = "/user/{username}")
	public List<Project> getUserRepos(@PathVariable("username") String login) {
		List<Project> projects = repositoryProject.findByOwnerLogin(login);
		if (projects.size() == 0) {
			projects = gitClient.getUserRepos(login);
			repositoryProject.save(projects);
		}
		return projects;
	}
}

