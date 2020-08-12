package com.example.homecompany.petproject.mapper;

import com.example.homecompany.petproject.dto.UserDto;
import com.example.homecompany.petproject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Mappings({
            @Mapping(target="id", source="dto.idDto"),
            @Mapping(target="name", source="dto.nameDto"),
            @Mapping(target="email", source="dto.emailDto"),
            @Mapping(target="password", source="dto.passwordDto")
    })
    User user (UserDto dto);

    @Mappings({
            @Mapping(target="idDto", source="entity.id"),
            @Mapping(target="nameDto", source="entity.name"),
            @Mapping(target="emailDto", source="entity.email"),
            @Mapping(target="passwordDto", source="entity.password")
    })
    UserDto userDto(User entity);
}
