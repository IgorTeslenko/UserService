package ru.myproj.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.myproj.userservice.model.User;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, BigInteger> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}

