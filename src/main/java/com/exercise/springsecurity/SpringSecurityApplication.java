package com.exercise.springsecurity;

import com.exercise.springsecurity.entity.User;
import com.exercise.springsecurity.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringSecurityApplication {
    private final UserRepository userRepository;

    public SpringSecurityApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initUsers() {
        var users = Stream.of(
                new User(111, "admin", "admin", "ROLE_ADMIN", "admin@gmail.com"),
                new User(112, "test", "test", "ROLE_USER", "tset@gmail.com"),
                new User(113, "dani", "dani", "ROLE_USER", "dani@gmail.com"),
                new User(114, "lidi", "lidi", "ROLE_USER", "lidi@gmail.com"),
                new User(115, "beza", "beza", "ROLE_USER", "beza@gmail.com")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
