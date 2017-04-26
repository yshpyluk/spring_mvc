package com.example.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by yshpyluk on 4/26/17.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Passport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String serialNumber;
}
