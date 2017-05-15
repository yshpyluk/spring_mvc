package com.example.domain.github;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yshpyluk on 5/9/17.
 */
@Setter
@Getter
@Entity
@Table(name = "git.users")
public class User {

	@Id
	protected Long id;

	private String login;
}
