package com.example.domain.github.entity.dto;

import com.example.domain.github.entity.GitUser;
import lombok.*;

/**
 * Created by yshpyluk on 5/21/17.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitUserDto {
	public String login;

	public GitUser toEntity() {
		return GitUser.builder()
				.login(login)
				.build();
	}
}
