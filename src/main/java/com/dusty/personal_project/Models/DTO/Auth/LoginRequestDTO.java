package com.dusty.personal_project.Models.DTO.Auth;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
