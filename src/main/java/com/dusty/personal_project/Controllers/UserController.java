package com.dusty.personal_project.Controllers;

import com.dusty.personal_project.AOP.AuthorizeRequest;
import com.dusty.personal_project.Models.DTO.User.UserCreateDTO;
import com.dusty.personal_project.Models.DTO.User.UserDetailsDTO;
import com.dusty.personal_project.Models.ResModel;
import com.dusty.personal_project.Services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResModel<UserDetailsDTO> register(
            @RequestBody UserCreateDTO userCreateDTO
            ) {
        return ResModel.ok(userService.registerUser(userCreateDTO));
    }

    @GetMapping("/{id}/info")
    @AuthorizeRequest(roles = {"ADMIN", "USER"})
    public ResModel<UserDetailsDTO> getUserDetailsById(
            @PathVariable UUID id
    ) {
        // fix: id phải trùng với id trong token
        // xử lý session
        return ResModel.ok(userService.getUserInfo(id));
    }

    @PostMapping("/{id}/update")
    @AuthorizeRequest(roles = {"ADMIN", "USER"})
    public String updateUserDetails(
            @PathVariable UUID id
    ) {
        return "Update User Details Successfully";
    }

}
