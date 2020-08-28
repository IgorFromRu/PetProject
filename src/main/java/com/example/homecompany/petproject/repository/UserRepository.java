package com.example.homecompany.petproject.repository;

import com.example.homecompany.petproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
