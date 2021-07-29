package ru.myproj.userservice.model.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private BigInteger id;
    @NonNull
    private String username;
    @NonNull
    private String password;
}
