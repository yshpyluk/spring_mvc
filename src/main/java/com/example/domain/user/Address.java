package com.example.domain.user;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

	@ManyToMany(mappedBy = "addresses")
	private List<User> users;
}
