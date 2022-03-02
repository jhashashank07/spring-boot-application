package com.test.demoAssignment.entity.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestToUser(UserRequest userRequest);

    @Mapping(source = "entityId", target = "userId")
    UserResponse userToUserResponse(User user);
}
