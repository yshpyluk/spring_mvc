package com.example.domain.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yshpyluk on 4/21/17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchUserException extends Exception {

	public NoSuchUserException(Long id) {
		super(String.format("Cannot find user with id: " + id));
	}
}
