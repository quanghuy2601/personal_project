package com.dusty.personal_project.Services.Impl;

import com.dusty.personal_project.Exception.BadRequestException;
import com.dusty.personal_project.Models.DTO.Auth.LoginDTO;
import com.dusty.personal_project.Models.DTO.Auth.LoginRequestDTO;
import com.dusty.personal_project.Models.DTO.Auth.TokenDTO;
import com.dusty.personal_project.Models.DTO.User.UserDetailsDTO;
import com.dusty.personal_project.Models.Entities.User;
import com.dusty.personal_project.Repositories.UserRepository;
import com.dusty.personal_project.Security.Jwt.JwtUtils;
import com.dusty.personal_project.Services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    private final List<String> listTokens = new ArrayList<>();

    @Override
    public LoginDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BadRequestException("Wrong username or password", 40001));

        if(!comparePassword(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Wrong username or password", 40001);
        }

        UserDetailsDTO userResponse = getUserDetails(user);

        String accessToken = jwtUtils.generateTokenFromUsername(user.getUsername(), user.getId(), user.getFullName(), user.getRole().name());

        String refreshToken = jwtUtils.generateRefreshTokenFromUsername(user.getUsername(), user.getId());

        TokenDTO tokens = new TokenDTO();
        tokens.setAccessToken(accessToken);
        tokens.setRefreshToken(refreshToken);

        listTokens.add(refreshToken);

        return new LoginDTO(userResponse, tokens);
    }

    public UserDetailsDTO getUserDetails(User user) {
        UserDetailsDTO userResponse = new UserDetailsDTO();

        userResponse.setId(user.getId().toString());
        userResponse.setUsername(user.getUsername());
        userResponse.setFullName(user.getFullName());
        userResponse.setRole(userResponse.getRole());

        return userResponse;
    }

    @Override
    public void logout(String refreshToken) {
        if(!Boolean.TRUE.equals(listTokens.contains(refreshToken))) {
            throw new BadRequestException("Wrong refresh token", 40004);
        }
        listTokens.remove(refreshToken);
    }

    @Override
    public LoginDTO refresh(String oldRefreshToken) {
        if(!Boolean.TRUE.equals(listTokens.contains(oldRefreshToken))) {
            throw new BadRequestException("Wrong refresh token", 40004);
        }

        String username =jwtUtils.getUsernameFromJwtToken(oldRefreshToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("Wrong refresh token", 40004));

        UserDetailsDTO userResponse = getUserDetails(user);

        String accessToken = jwtUtils.generateTokenFromUsername(
                user.getUsername(), user.getId(), user.getFullName(), user.getRole().name());

        String refreshToken = jwtUtils.generateRefreshTokenFromUsername(user.getUsername(), user.getId());

        TokenDTO tokens = new TokenDTO();
        tokens.setAccessToken(accessToken);
        tokens.setRefreshToken(refreshToken);

        listTokens.remove(oldRefreshToken);

        listTokens.add(refreshToken);

        return new LoginDTO(userResponse, tokens);
    }

    @Override
    public boolean comparePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
