package com.example.domain.github;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by yshpyluk on 5/9/17.
 */
@Getter
@Setter
@Entity
@Table(name = "git.projects")
public class Project {
	@Id
	private Long id;
	private String name;
	private String full_name;
	@ManyToOne
	private User owner;
}
