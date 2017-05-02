package com.example.domain.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by yshpyluk on 5/1/17.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int buildingNumber;
	private String street;
}
