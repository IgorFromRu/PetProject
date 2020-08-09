package com.example.homecompany.petproject.service.impl;

import com.example.homecompany.petproject.model.User;
import com.example.homecompany.petproject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    private Map<Long, User> userMap = new HashMap<>();

    private AtomicLong idGen = new AtomicLong(0);


    @Override
    public User create(User user) {
        final Long id = idGen.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    @Override
    public User getId(long id) {
        if (userMap.get(id) == null) {
            throw new IllegalArgumentException("user not found, id=" + id);
        }
        return userMap.get(id);
    }

    @Override
    public User update(long id, User user) {
        if (userMap.containsKey(id)) {
            user.setId(id);
            userMap.put(id, user);
            return userMap.get(id);
        }
        throw new IllegalArgumentException("user not updated, id=" + id);
    }

    @Override
    public boolean delete(long id) {
        userMap.remove(id);
        return userMap.remove(id) != null;
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(userMap.values());
    }
}
