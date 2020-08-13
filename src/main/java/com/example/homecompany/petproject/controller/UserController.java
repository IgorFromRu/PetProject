package com.example.homecompany.petproject.controller;

import com.example.homecompany.petproject.dto.UserDto;
import com.example.homecompany.petproject.mapper.ModelAndDto;
import com.example.homecompany.petproject.model.User;
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
    private ModelAndDto modelAndDto;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        final User user = modelAndDto.user(userDto);
        userService.createUser(user);
        final UserDto userReturn = modelAndDto.userDto(user);
        return userReturn != null
                ? new ResponseEntity<>(userReturn, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getListUsers() {
        final List<User> users = userService.getListUsers();
        final List<UserDto> usersDto = new ArrayList<>();
        for (User user: users) {
            usersDto.add(modelAndDto.userDto(user));
        }
        return usersDto != null && !usersDto.isEmpty()
                ? new ResponseEntity<>(usersDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") long id) {
        final User user = userService.getUserById(id);
        final UserDto userDto = modelAndDto.userDto(user);
        return userDto != null
                ? new ResponseEntity<>(userDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") long id, @RequestBody UserDto userDto) {
        User user = modelAndDto.user(userDto);
        userService.updateUser(id, user);
        UserDto userDtoUpdated = modelAndDto.userDto(user);
        return userDtoUpdated != null
                ? new ResponseEntity<>(userDtoUpdated, HttpStatus.OK)
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
