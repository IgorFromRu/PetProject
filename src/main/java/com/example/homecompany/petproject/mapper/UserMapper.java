package com.example.homecompany.petproject.mapper;

import com.example.homecompany.petproject.dto.UserDto;
import com.example.homecompany.petproject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {

    @Mappings({
            @Mapping(target="id", source="dto.idDto"),
            @Mapping(target="name", source="dto.nameDto"),
            @Mapping(target="email", source="dto.emailDto"),
            @Mapping(target="password", source="dto.passwordDto")
    })
    User user (UserDto dto);

    @Mappings({
            @Mapping(target="idDto", source="user.id"),
            @Mapping(target="nameDto", source="user.name"),
            @Mapping(target="emailDto", source="user.email"),
            @Mapping(target="passwordDto", source="user.password")
    })
    UserDto userDto(User user);
}
