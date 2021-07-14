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
    private BigInteger id;

    @NonNull
    private String username;

    @NonNull
    private String password;
}


