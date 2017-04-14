package com.example.service;

import com.example.dao.GenericDAO;
import com.example.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

/**
 * Created by yshpyluk on 4/5/17.
 */
@Service("in_memory_service")
public class UserServiceInMemory implements UserService {

    @Autowired
    private GenericDAO<User> userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User get(Long i) {
        return userDao.get(i);
    }

    @Override
    public void add(String name) {
        userDao.add(new User(name));
    }

    @Override
    public void remove(Long id) {
        removeUserByPredicate(u -> u.getId() == id);
    }

    private void removeUserByPredicate(Predicate<User> userPredicate) {
        Optional<User> userToDelete = userDao.getAll().stream()
                .filter(userPredicate)
                .findFirst();
        userToDelete.ifPresent(userDao::remove);
    }

    @Override
    public User update(Long id) {
        return null;
    }
}
