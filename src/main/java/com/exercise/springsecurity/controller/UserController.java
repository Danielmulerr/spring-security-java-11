package com.exercise.springsecurity.controller;

import com.exercise.springsecurity.dto.AuthRequest;
import com.exercise.springsecurity.dto.UserDto;
import com.exercise.springsecurity.entity.User;
import com.exercise.springsecurity.service.JwtService;
import com.exercise.springsecurity.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")

public class UserController {
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/test-user")
//    @PreAuthorize("hasAuthority(ROLE_ADMIN)")
    public UserDto addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/auth")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        var authResult = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authRequest.getUsername(), authRequest.getPassword()))
                .isAuthenticated();
        if (authResult)
            return jwtService.generateToken(authRequest.getUsername());
        else
            throw new RuntimeException("Unable to login! Please check username and password.");
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        System.out.println("Username: " + principal.getName());
        return principal;
    }
}
