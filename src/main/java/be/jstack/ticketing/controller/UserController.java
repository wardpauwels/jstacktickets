package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.User;
import be.jstack.ticketing.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Stream<User> findAllUsers() {
        return userService.findAllUsers().stream();
    }

    @GetMapping("/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        userService.addUser(user);
    }
}