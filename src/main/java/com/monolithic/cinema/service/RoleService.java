package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.RoleRequest;
import com.monolithic.cinema.dto.Response.RoleResponse;
import com.monolithic.cinema.entity.Role;
import com.monolithic.cinema.enums.ErrorCode;
import com.monolithic.cinema.exception.CustomException;
import com.monolithic.cinema.mapper.RoleMapper;
import com.monolithic.cinema.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;


    public List<RoleResponse> getRoles(){
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Transactional(rollbackFor ={CustomException.class})
    public RoleResponse createRole(RoleRequest roleRequest){
        if (roleRepository.existsByName(roleRequest.getName()))
            throw new CustomException(ErrorCode.RESOURCE_ALREADY_EXISTS,"Role");

        Role role = roleMapper.toRole(roleRequest);

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Transactional(rollbackFor ={CustomException.class})
    public RoleResponse updateRole(String id, RoleRequest roleRequest){
       Role role = roleRepository.findById(id).orElseThrow(
               ()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"Role"));

       roleMapper.updateRole(role, roleRequest);

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Transactional()
    public void deleteRole(String id){
        Role role = roleRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"Role"));
        roleRepository.deleteById(id);
    }

}
