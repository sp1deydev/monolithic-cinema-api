package com.monolithic.cinema.controller;

import com.monolithic.cinema.dto.Request.UserRequest;
import com.monolithic.cinema.dto.Response.UserResponse;
import com.monolithic.cinema.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @PostMapping
    public UserResponse createUser( @Valid @RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable String id,@Valid @RequestBody UserRequest userRequest){
        return userService.updateUser(id, userRequest);
    }


}
