package ru.myproj.userservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.myproj.userservice.exceptions.UserNotFoundException;
import ru.myproj.userservice.models.User;
import ru.myproj.userservice.models.dtos.UserDto;
import ru.myproj.userservice.repos.UserRepository;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(UserDto userDto) {
        return userRepository.save(new User(userDto.getUsername(), userDto.getPassword()));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(BigInteger id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User updateUser(UserDto userDto) {
        if (!userRepository.existsById(userDto.getId())) {
            return addUser(userDto);
        }
        User user = findById(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    public void deleteUserById(BigInteger userId) {
        userRepository.deleteById(userId);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

}
