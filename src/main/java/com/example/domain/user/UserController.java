package com.example.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
        //TODO implement
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/update")
    public String updateUser(@RequestBody User user) {
        //TODO use status code to manage response
        User updatedUser = userService.update(user);
        return String.format("User %s was updated", updatedUser.getName());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addUser(@RequestBody @Validated User user) {
        if (userService.getByName(user.getName()).isPresent()) {
            //Create custom Runtime Exception for this case
            throw new RuntimeException("User exists");
        }
        userService.add(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}")
    public String removeUser(@PathVariable Long userId) {
        User user = userService.get(userId);
        userService.remove(userId);
        //TODO use status code to manage responsessss
        return String.format("User %s was deleted", user.getName());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleError(Exception ex) {
        return ex.getMessage();
    }

}