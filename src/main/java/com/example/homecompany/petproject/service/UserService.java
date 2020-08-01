package com.example.homecompany.petproject.service;

import com.example.homecompany.petproject.model.User;

public interface UserService {

    User create (User user);

    User getId (int id);

    User update (int id, User user);

    void delete (int id);
}
