package com.example.homecompany.petproject.model;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private Long id;

}
