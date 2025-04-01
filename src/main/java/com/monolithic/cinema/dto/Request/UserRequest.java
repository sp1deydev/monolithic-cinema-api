package com.monolithic.cinema.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequest {

    @Size(min = 3, message = "INVALID_LENGTH_INPUT:Username")
    String username;

    @Size(min = 3, message = "INVALID_LENGTH_INPUT:Password")
    String password;

    @Email(message = "INVALID_FORMAT_INPUT:Email")
    String email;

    String roleName;
}
