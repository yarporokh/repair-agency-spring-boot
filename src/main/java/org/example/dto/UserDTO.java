package org.example.dto;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class UserDTO {
    String email;
    String password;
    String firstName;
    String lastName;
}
