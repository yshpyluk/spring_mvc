package com.example.domain.user;

import com.example.domain.user.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yshpyluk on 4/15/17.
 */
@RestController
@RequestMapping("/group")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable long id) {
        return userService.get(id);
    }

    @PutMapping(value = "/user/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody User user) throws NoSuchUserException {
        userService.update(user);
    }

    @PostMapping(value = "/user/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addUser(@RequestBody @Validated User user) {
        if (userService.getByName(user.getName()).isPresent()) {
            //Create custom Runtime Exception for this case
            throw new RuntimeException("User exists");
        }
        userService.add(user);
    }

    @DeleteMapping(value = "/user/{userId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void removeUser(@PathVariable Long userId) throws NoSuchUserException {
        userService.remove(userId);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleError(Exception ex) throws Exception {
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }

        return ex.getMessage();
    }
}