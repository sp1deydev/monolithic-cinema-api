package com.monolithic.cinema.mapper;


import com.monolithic.cinema.dto.Request.UserRequest;
import com.monolithic.cinema.dto.Response.UserResponse;
import com.monolithic.cinema.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roleName", target = "role.name")
    User toUser(UserRequest userRequest);

    @Mapping(source = "role.name", target = "roleName")
    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget User user, UserRequest userRequest);


}
