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
        return userFromDatabase(userRepository.findById(id).get().getId(),
                userRepository.findById(id).get().getName(),
                userRepository.findById(id).get().getEmail(),
                userRepository.findById(id).get().getPassword());
    }

    @Override
    public User updateUser(long id, User user) {
        if (userRepository.findById(id) != null) {
            userRepository.findById(id).get().setName(user.getName());
            userRepository.findById(id).get().setEmail(user.getEmail());
            userRepository.findById(id).get().setPassword(user.getPassword());

            return userFromDatabase(id,
                    userRepository.findById(id).get().getName(),
                    userRepository.findById(id).get().getEmail(),
                    userRepository.findById(id).get().getPassword());
        }
        throw new IllegalArgumentException("user not found, id=" + id);
    }

    @Override
    public boolean deleteUser(long id) {
        userRepository.deleteById(id);
        if (userRepository.findById(id) == null) {
            return true;
        }
        else return false;
    }

    @Override
    public List<User> getListUsers() {
        return (List<User>) userRepository.findAll();
    }

    private User userFromDatabase(long id, String name, String email, String password){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
