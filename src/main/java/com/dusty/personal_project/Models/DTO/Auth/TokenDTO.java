package com.dusty.personal_project.Models.DTO.Auth;

import lombok.Data;

@Data
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
}
