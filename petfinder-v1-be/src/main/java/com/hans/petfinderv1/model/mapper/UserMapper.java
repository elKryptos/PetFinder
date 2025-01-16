package com.hans.petfinderv1.model.mapper;

import com.hans.petfinderv1.model.dto.UserDto;
import com.hans.petfinderv1.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<User> userList);
    List<User> toEntityLis(List<UserDto> userDtoList);
    // mapper per aggiornare una entity
    @Mapping(target = "userId", ignore = true)
    User updateUser(@MappingTarget User user, UserDto userDto);
}
