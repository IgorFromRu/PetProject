package com.example.homecompany.petproject.service.impl;

import com.example.homecompany.petproject.model.User;
import com.example.homecompany.petproject.repository.UserRepository;
import com.example.homecompany.petproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(long id, User user) {
        if (userRepository.findById(id) != null) {
            user.setId(id);
            userRepository.save(user);
            return userRepository.findById(id).get();
        }
        throw new IllegalArgumentException("user not found, id=" + id);
    }

    @Override
    public boolean deleteUser(long id) {
        userRepository.deleteById(id);
        if (userRepository.findById(id) == null) {
            return true;
        } else return false;
    }

    @Override
    public List<User> getListUsers() {
        return userRepository.findAll();
    }
}
