package com.exercise.springsecurity.dto;

//import javax.persistence.Entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    private int user_id;
    @NotNull(message = "Name can't be null")
    private String name;
    @NotNull(message = "Role can't be null")
    private String role;
    @NotNull(message = "email can't be null")
    @Email(message = "Invalid email address")
    private String email;
}
