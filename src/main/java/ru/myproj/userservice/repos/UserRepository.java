package ru.myproj.userservice.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.myproj.userservice.models.User;

import java.math.BigInteger;

public interface UserRepository extends MongoRepository<User, BigInteger> {

}
