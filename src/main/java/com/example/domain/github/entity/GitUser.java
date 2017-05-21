package com.example.domain.github.entity;

import com.example.domain.github.entity.dto.GitUserDto;
import lombok.*;

import javax.persistence.*;

/**
 * Created by yshpyluk on 5/9/17.
 */
@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "git.users")
public class GitUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	public String login;

	public GitUserDto toDto() {
		return GitUserDto.builder()
				.login(login)
				.build();
	}
}
