package com.example.domain.github;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yshpyluk on 5/14/17.
 */
public interface RepositoryProject extends JpaRepository<Project, Long> {

	List<Project> findByOwnerLogin(String login);
}
