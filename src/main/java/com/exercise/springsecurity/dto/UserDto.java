package com.exercise.springsecurity.dto;

//import javax.persistence.Entity;

import lombok.Data;

@Data
public class UserDto {
    private int user_id;
    private String name;
    private String role;
    private String email;
}
