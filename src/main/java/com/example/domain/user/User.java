package com.example.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by yshpyluk on 4/5/17.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@NotNull
	private String name;

	@Convert(converter = BirthdayConverter.class)
	private LocalDate birthday;

	@OneToOne(cascade = CascadeType.ALL)
	private Passport passport;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "join_address_user_table",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "address_id"))
	private List<Address> addresses;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "project",
			joinColumns = @JoinColumn(name = "user_id")
	)
	@Column(name = "project")
	private List<String> projects;

	public UserDto convertToDto() {
		return UserDto.builder().name(this.name).build();
	}

	@Override
	public boolean equals(Object obj) {
		User compareUser = (User) obj;
		return this.id == compareUser.getId() &&
				this.getName().equals(compareUser.getName());
	}
}
