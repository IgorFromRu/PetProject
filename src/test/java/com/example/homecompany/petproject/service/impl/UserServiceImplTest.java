package com.example.homecompany.petproject.service.impl;

import com.example.homecompany.petproject.model.User;
import com.example.homecompany.petproject.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private static Long USER1_ID;
    private static final String USER1_NAME = "User1";
    private static final String USER1_EMAIL = "user1@mail.com";
    private static final String USER1_PASSWORD = "pass1";
    private static Long USER2_ID;
    private static final String USER2_NAME = "User2";
    private static final String USER2_EMAIL = "user2@mail.com";
    private static final String USER2_PASSWORD = "pass2";


    @Resource
    UserService userService;

    @Before
    public void setup() {
        User user1 = new User();
        user1.setName(USER1_NAME);
        user1.setEmail(USER1_EMAIL);
        user1.setPassword(USER1_PASSWORD);
        user1 = userService.createUser(user1);
        USER1_ID = user1.getId();
        User user2 = new User();
        user2.setName(USER2_NAME);
        user2.setEmail(USER2_EMAIL);
        user2.setPassword(USER2_PASSWORD);
        user2 = userService.createUser(user2);
        USER2_ID = user2.getId();
    }

    @After
    public void cleanData() {
        userService.getListUsers().forEach(user -> {
            userService.deleteUser(user.getId());
        });
    }

    @Test
    public void create() {
        User user = new User();
        user.setName("Timur");
        user.setEmail("tim@mail.com");
        user.setPassword("pass");
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
    }

    @Test
    public void getId() {
        User user = new User();
        user.setName("Timur");
        user.setEmail("tim@mail.com");
        user.setPassword("pass");
        userService.createUser(user);
        assertNotNull(user);
        assertEquals("Timur", user.getName());
        assertEquals("tim@mail.com", user.getEmail());
        assertEquals("pass", user.getPassword());
    }

    @Test
    public void update() {
        User user1WithDataFromUser2 = new User();
        user1WithDataFromUser2.setId(USER1_ID);
        user1WithDataFromUser2.setName(USER2_NAME);
        user1WithDataFromUser2.setEmail(USER2_EMAIL);
        user1WithDataFromUser2.setPassword(USER2_PASSWORD);
        User user1 = userService.updateUser(USER1_ID, user1WithDataFromUser2);
        assertEquals(user1WithDataFromUser2, user1);
    }

    @Test
    public void delete() {
        boolean delete = userService.deleteUser(USER1_ID);
        assertTrue(delete);
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(USER1_ID));
    }

    @Test
    public void readAll() {
        List<User> users = userService.getListUsers();
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(user -> USER1_ID.equals(user.getId())));
        assertTrue(users.stream().anyMatch(user -> USER2_ID.equals(user.getId())));
    }
}
