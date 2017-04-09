package com.example.service;

import com.example.dao.GenericDAO;
import com.example.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * Created by yshpyluk on 4/5/17.
 */
@Service
public class UserServiceInMemory implements UserService {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    private GenericDAO<User> userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User get(int i) {
        return userDao.get(i);
    }

    @Override
    public void add(String name) {
        userDao.add(new User(name, counter.getAndIncrement()));
    }

    @Override
    public void remove(int id) {
        removeUserByPredicate(u -> u.getId() == id);
    }

    @Override
    public void remove(String name) {
       removeUserByPredicate(u -> u.getName().toLowerCase().equals(name.toLowerCase()));
    }

    private void removeUserByPredicate(Predicate<User> userPredicate) {
        Optional<User> userToDelete = userDao.getAll().stream()
                .filter(userPredicate)
                .findFirst();
        userToDelete.ifPresent(userDao::remove);
    }

    @Override
    public User update(int id) {
        return null;
    }

    @Override
    public User update(String name) {
        return null;
    }
}
