package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Request.RoleRequest;
import com.monolithic.cinema.dto.Response.RoleResponse;
import com.monolithic.cinema.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @GetMapping
    public List<RoleResponse> getRoles(){
        return  roleService.getRoles();
    }

    @PostMapping
    public RoleResponse createRole(@RequestBody RoleRequest roleRequest){
        return roleService.createRole(roleRequest);
    }

    @PutMapping("/{id}")
    public RoleResponse updateRole(@PathVariable String id, @RequestBody RoleRequest roleRequest){
        return roleService.updateRole(id,roleRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable String id){
        roleService.deleteRole(id);
    }

}
