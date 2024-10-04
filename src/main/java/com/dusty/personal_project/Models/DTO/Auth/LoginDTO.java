package com.dusty.personal_project.Models.DTO.Auth;

import com.dusty.personal_project.Models.DTO.User.UserDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private UserDetailsDTO userInfo;
    private TokenDTO token;
}
