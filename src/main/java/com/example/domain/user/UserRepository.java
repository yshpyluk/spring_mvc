package com.example.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by yshpyluk on 4/13/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
}
