package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.User;
import be.jstack.ticketing.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
@Api(value = "User controller", description = "Retrieve info about saved users, make new users or adapt existing ones.")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "View a list of all the current users", response = User.class, responseContainer = "List")
    public Stream<User> findAllUsers() {
        return userService.findAll().stream();
    }

    @GetMapping("/{username}")
    @ApiOperation(value = "View info about the user with given ID", response = User.class)
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping
    @ApiOperation(value = "Submit a new user", response = User.class)
    public User addNewUser(@RequestBody User user) {
        return userService.add(user);
    }

    @PatchMapping("/{username}/roles")
    @ApiOperation(value = "Add an authority role to the user with given username", response = User.class)
    public User addAuthorityToUser(@PathVariable String username, @RequestBody String role) {
        JSONObject jsonBody = new JSONObject(role);
        return userService.addAuthorityToUser(username, jsonBody.getString("role"));
    }

    @DeleteMapping("/{username}/roles")
    @ApiOperation(value = "Remove an authority role from the user with given username", response = User.class)
    public User removeAuthorityFromUser(@PathVariable String username, @RequestBody String role) {
        JSONObject jsonBody = new JSONObject(role);
        return userService.removeAuthorityForUser(username, jsonBody.getString("role"));
    }
}