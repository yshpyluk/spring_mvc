package com.example.entity;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yshpyluk on 4/5/17.
 */
@Entity
@Table(name = "users")
public class User {

	private static AtomicLong counter = new AtomicLong();

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	User() {
		//for hibernate
	}

	public User(String name) {
		this.id = counter.incrementAndGet();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
