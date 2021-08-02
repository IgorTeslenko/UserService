package ru.myproj.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.myproj.userservice.model.User;
import ru.myproj.userservice.model.dto.UserDTO;
import ru.myproj.userservice.service.UserService;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Deprecated
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDTO userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<User> showAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/personal")
    public String returnPersonalInfo() {
        return "personal info";
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable BigInteger userId) {
        return userService.findById(userId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User updateUser(@RequestBody UserDTO userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@RequestParam BigInteger userId) {
        userService.deleteUserById(userId);
    }

    //для отладки, чтобы быстро базу очищать
    @DeleteMapping("/flush_all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }
}
