package com.course.project.arina.dto;

import com.course.project.arina.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Role role;
    String name;
    String surname;
    String email;
    String password;

}
