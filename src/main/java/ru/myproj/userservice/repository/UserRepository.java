package ru.myproj.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.myproj.userservice.model.User;

import java.math.BigInteger;

public interface UserRepository extends MongoRepository<User, BigInteger> {

}
