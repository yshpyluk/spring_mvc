package com.example.domain.github;

import com.example.domain.github.entity.GitUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by yshpyluk on 5/14/17.
 */
public interface GitUserRepository extends JpaRepository<GitUser, Long> {

	Optional<GitUser> findByLogin(String login);
}
