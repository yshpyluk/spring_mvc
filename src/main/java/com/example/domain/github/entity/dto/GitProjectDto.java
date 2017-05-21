package com.example.domain.github.entity.dto;

import com.example.domain.github.entity.GitProject;
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
public class GitProjectDto {

	private String name;
	private String full_name;
	private GitUser owner;

	public GitProject toEntity() {
		return GitProject.builder()
				.name(name)
				.full_name(full_name)
				.owner(owner)
				.build();
	}
}
