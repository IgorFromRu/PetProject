package com.example.homecompany.petproject.service.impl;

import com.example.homecompany.petproject.model.User;
import com.example.homecompany.petproject.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UserServiceImpl implements UserService {

    private Map <Integer, User> userMap = new HashMap<>();

    private AtomicInteger idGen = new AtomicInteger(0);


    @Override
    public User create(User user) {
        int id = idGen.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    @Override
    public User getId(int id) {
        if(userMap.get(id) == null ){
            throw new IllegalArgumentException("user not found, id=" + id);
        }
        return userMap.get(id);
    }

    @Override
    public User update(int id, User user) {
        User userUpdate = getId(id);
        if (user.getName()!=null) userUpdate.setName(user.getName());
        if (user.getEmail()!=null) userUpdate.setEmail(user.getEmail());
        if (user.getPassword()!=null) userUpdate.setPassword(user.getPassword());
        return userUpdate;
    }

    @Override
    public void delete(int id) {
        userMap.remove(getId(id));
    }
}
