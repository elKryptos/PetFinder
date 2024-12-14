package com.hans.petfinderv1.mapper;

import com.hans.petfinderv1.dto.UserDto;
import com.hans.petfinderv1.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtoLis(List<User> userList);
    List<User> toEntityLis(List<UserDto> userDtoList);
}
