package com.example.homecompany.petproject.mapper;

import com.example.homecompany.petproject.dto.UserDto;
import com.example.homecompany.petproject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {

    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="name", source="dto.name"),
            @Mapping(target="email", source="dto.email"),
            @Mapping(target="password", source="dto.password")
    })
    User user (UserDto dto);

    @Mappings({
            @Mapping(target="id", source="user.id"),
            @Mapping(target="name", source="user.name"),
            @Mapping(target="email", source="user.email"),
            @Mapping(target="password", source="user.password")
    })
    UserDto userDto(User user);
}
