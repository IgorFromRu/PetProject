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
    public User createUser(User user) {
        final Long id = idGen.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    @Override
    public User getIdByUser(long id) {
        if (userMap.get(id) == null) {
            throw new IllegalArgumentException("user not found, id=" + id);
        }
        return userMap.get(id);
    }

    @Override
    public boolean updateUser(long id, User user) {
        if (userMap.containsKey(id)) {
            user.setId(id);
            userMap.put(id, user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(long id) {
        userMap.remove(getIdByUser(id));
        return userMap.remove(getIdByUser(id)) != null;
    }

    @Override
    public List<User> getListUsers() {
        return new ArrayList<>(userMap.values());
    }
}
