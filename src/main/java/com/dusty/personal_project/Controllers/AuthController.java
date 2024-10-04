package com.dusty.personal_project.Controllers;

import com.dusty.personal_project.Models.DTO.Auth.LoginDTO;
import com.dusty.personal_project.Models.DTO.Auth.LoginRequestDTO;
import com.dusty.personal_project.Models.ResModel;
import com.dusty.personal_project.Services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/login")
    public ResModel<LoginDTO> login(
            @RequestBody LoginRequestDTO loginRequest
    ) {
        return ResModel.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResModel<LoginDTO> register(
            @RequestParam String refreshToken
    ) {
        return ResModel.ok(authService.refresh(refreshToken));
    }

    @PostMapping("/logout")
    public ResModel<String> logout(
            @RequestParam String refreshToken
    ) {
        authService.logout(refreshToken);
        return ResModel.ok("Successfully");
    }


}
