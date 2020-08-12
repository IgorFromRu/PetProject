package com.example.homecompany.petproject.service;

import com.example.homecompany.petproject.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getIdByUser(long id);

    boolean updateUser(long id, User user);

    boolean deleteUser(long id);

    List<User> getListUsers();
}
