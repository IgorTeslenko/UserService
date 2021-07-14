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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDTO userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping
    public List<User> showAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable BigInteger userId) {
        return userService.findById(userId);
    }

    @PutMapping
    public User updateUser(@RequestBody UserDTO userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping
    public void deleteUserById(@RequestParam BigInteger userId) {
        userService.deleteUserById(userId);
    }

    //для отладки, чтобы быстро базу очищать
    @DeleteMapping("/flush_all")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }
}
