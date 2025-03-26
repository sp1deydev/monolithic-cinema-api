package com.monolithic.cinema.dto.Response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {

    String id;
    String username;
    String password;
    String email;
    String roleName;

}
