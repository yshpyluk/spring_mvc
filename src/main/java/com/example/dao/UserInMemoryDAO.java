package com.example.dao;

import com.example.entity.User;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by yshpyluk on 4/5/17.
 */
@Repository
public class UserInMemoryDAO implements GenericDAO<User> {

    private final List<User> users = new ArrayList<>();

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User get(Long i) {
        return users.stream()
                .filter(u -> Objects.equals(u.getId(), i))
                .findFirst()
                .get();
    }

    @Override
    public void add(User entity) {
        users.add(entity);
    }

    @Override
    public void remove(User entity) {
        users.removeIf(u -> u.getId() != entity.getId());
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
