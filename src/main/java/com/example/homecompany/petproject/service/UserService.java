package com.example.homecompany.petproject.service;

import com.example.homecompany.petproject.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User getId(long id);

    User update(long id, User user);

    boolean delete(long id);

    List<User> readAll();
}
