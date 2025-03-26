package com.monolithic.cinema.mapper;


import com.monolithic.cinema.dto.Request.RoleRequest;
import com.monolithic.cinema.dto.Response.RoleResponse;
import com.monolithic.cinema.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "id", ignore = true)
    void updateRole(@MappingTarget Role role, RoleRequest roleRequest);

}
