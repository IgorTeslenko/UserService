package ru.myproj.userservice.model.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserDTO {
    private BigInteger id;
    private String username;
    private String password;
}
