package ru.myproj.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.myproj.userservice.exception.UserNotFoundException;
import ru.myproj.userservice.model.User;
import ru.myproj.userservice.model.dto.UserDTO;
import ru.myproj.userservice.repository.UserRepository;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<?> createUser(UserDTO userDto) {
        if (userExist(userDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        User user = new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                List.of("User"));
        userRepository.save(user);
        return new ResponseEntity<>("Successful registration", HttpStatus.CREATED);
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

    public ResponseEntity<?> updateUser(UserDTO userDTO) {
        User user;
        try {
            user = findById(userDTO.getId());
        } catch (UserNotFoundException e) {
            createUser(userDTO);
            return new ResponseEntity<>("Successful registration", HttpStatus.CREATED);
        }
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return new ResponseEntity<>("Successful registration", HttpStatus.CREATED);
    }

    public void deleteUserById(BigInteger userId) {
        userRepository.deleteById(userId);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
//        return new User("user1", "password1", new ArrayList<>());
    }
}
