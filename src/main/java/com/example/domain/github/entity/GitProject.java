package com.example.domain.github.entity;

import com.example.domain.github.entity.dto.GitProjectDto;
import lombok.*;

import javax.persistence.*;

/**
 * Created by yshpyluk on 5/9/17.
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "git.projects")
public class GitProject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String full_name;
	@ManyToOne
	private GitUser owner;

	public GitProjectDto toDto() {
		return GitProjectDto.builder()
				.name(name)
				.full_name(full_name)
				.owner(owner)
				.build();
	}
}
