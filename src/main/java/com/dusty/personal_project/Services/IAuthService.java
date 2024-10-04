package com.dusty.personal_project.Services;

import com.dusty.personal_project.Models.DTO.Auth.LoginDTO;
import com.dusty.personal_project.Models.DTO.Auth.LoginRequestDTO;

public interface IAuthService {
    LoginDTO login(LoginRequestDTO loginRequest);

    void logout(String refreshToken);

    LoginDTO refresh(String oldRefreshToken);

    boolean comparePassword(String rawPassword, String encodedPassword);
}
