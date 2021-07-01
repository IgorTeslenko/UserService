package ru.myproj.userservice.models.dtos;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserDto {
    private BigInteger id;
    private String username;
    private String password;
}
