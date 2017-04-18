package com.example.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by yshpyluk on 4/5/17.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	@NotNull
	private String name;

}
