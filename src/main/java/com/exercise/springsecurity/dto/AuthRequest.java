package com.exercise.springsecurity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {
    @NotNull(message = "Username can't be null")
    private String username;
    @NotNull(message = "Password can't be null")
    private String password;
}
