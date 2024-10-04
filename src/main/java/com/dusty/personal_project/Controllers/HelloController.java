package com.dusty.personal_project.Controllers;

import com.dusty.personal_project.Models.DTO.User.UserDetailsDTO;
import com.dusty.personal_project.Models.ResModel;
import com.dusty.personal_project.Services.Impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HelloController {

    private final UserService userService;

    @GetMapping
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/getAllUsers")
    public ResModel<List<UserDetailsDTO>> getAllUsers() {
        return ResModel.ok(userService.getAllUsers());
    }

    // a small update

}
