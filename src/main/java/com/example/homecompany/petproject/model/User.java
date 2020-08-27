package com.example.homecompany.petproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = true)
    private long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

}
