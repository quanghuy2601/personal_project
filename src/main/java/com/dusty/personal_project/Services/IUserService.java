package com.dusty.personal_project.Services;

import com.dusty.personal_project.Models.DTO.User.UserCreateDTO;
import com.dusty.personal_project.Models.DTO.User.UserDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    UserDetailsDTO registerUser(UserCreateDTO userCreate);

    UserDetailsDTO getUserInfo(UUID userId);

    List<UserDetailsDTO> getAllUsers();
}
