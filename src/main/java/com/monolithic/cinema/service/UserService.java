package com.monolithic.cinema.service;

import com.monolithic.cinema.dto.Request.UserRequest;
import com.monolithic.cinema.dto.Response.UserResponse;
import com.monolithic.cinema.entity.Role;
import com.monolithic.cinema.entity.User;
import com.monolithic.cinema.enums.ErrorCode;
import com.monolithic.cinema.exception.CustomException;
import com.monolithic.cinema.mapper.UserMapper;
import com.monolithic.cinema.repository.RoleRepository;
import com.monolithic.cinema.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Transactional()
    public UserResponse createUser(UserRequest userRequest){
        if (userRepository.existsByUsername(userRequest.getUsername()))
            throw new CustomException(ErrorCode.RESOURCE_ALREADY_EXISTS,"User");

        if (userRequest.getRoleName() == null)
        {
            userRequest.setRoleName("USER");
        }

        Role role = roleRepository.findByName(userRequest.getRoleName()).orElseThrow(
                ()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"Role"));

        User user = userMapper.toUser(userRequest);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));

    }

    @Transactional()
    public UserResponse updateUser(String id, UserRequest userRequest){
        User user = userRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"User"));

        userMapper.updateUser(user,userRequest);

        Role role = roleRepository.findByName(userRequest.getRoleName()).orElseThrow(
                ()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"Role"));
        user.setRole(role);

        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Transactional()
    public void deleteUser(String id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
        }
        else {
            throw new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"User");
        }
    }


    public UserResponse getUser(String id)
    {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(
                ()-> new CustomException(ErrorCode.RESOURCE_NOT_FOUND,"User")));
    }

}
