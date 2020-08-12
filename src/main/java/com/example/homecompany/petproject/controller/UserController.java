package com.example.homecompany.petproject.controller;

import com.example.homecompany.petproject.dto.UserDto;
import com.example.homecompany.petproject.mapper.UserMapperImpl;
import com.example.homecompany.petproject.model.User;
import com.example.homecompany.petproject.mapper.UserMapper;
import com.example.homecompany.petproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.user(userDto);
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getListUsers() {
        final List<User> users = userService.getListUsers();
        final List<UserDto> usersDto = new ArrayList<>();
        for (User user: users) {
            usersDto.add(userMapper.userDto(user));
        }
        return usersDto != null && !users.isEmpty()
                ? new ResponseEntity<>(usersDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id) {
        final User user = userService.getIdByUser(id);
        final UserDto userDto = userMapper.userDto(user);
        return userDto != null
                ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") long id, @RequestBody UserDto userDto) {
        User user = userMapper.user(userDto);
        final boolean updated = userService.updateUser(id, user);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") long id) {
        final boolean deleted = userService.deleteUser(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
