package com.example.homecompany.petproject.mapper;

import com.example.homecompany.petproject.dto.UserDto;
import com.example.homecompany.petproject.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User user (UserDto dto);

    UserDto userDto(User user);
}
