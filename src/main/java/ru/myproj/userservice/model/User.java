package ru.myproj.userservice.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Data
@RequiredArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    protected BigInteger id;

    @NonNull
    protected String username;

    @NonNull
    protected String password;
}


