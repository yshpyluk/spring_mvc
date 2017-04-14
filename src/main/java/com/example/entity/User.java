package com.example.entity;

import javax.persistence.*;

/**
 * Created by yshpyluk on 4/5/17.
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	public User(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
