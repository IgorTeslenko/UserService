package ru.myproj.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.myproj.userservice.exception.UserNotFoundException;
import ru.myproj.userservice.model.User;
import ru.myproj.userservice.model.dto.UserDTO;
import ru.myproj.userservice.repository.UserRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User createUser(UserDTO userDto) {
        return userRepository.save(new User(userDto.getUsername(), userDto.getPassword(), List.of("user")));
    }

    public boolean userExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(BigInteger userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
    }

    public User updateUser(UserDTO userDTO) {
        User user;
        try {
            user = findById(userDTO.getId());
        } catch (UserNotFoundException e) {
            return createUser(userDTO);
        }
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    public void deleteUserById(BigInteger userId) {
        userRepository.deleteById(userId);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));

        return null;
    }
}
