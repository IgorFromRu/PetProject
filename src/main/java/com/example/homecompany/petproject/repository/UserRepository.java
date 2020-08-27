package com.example.homecompany.petproject.repository;

import com.example.homecompany.petproject.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
