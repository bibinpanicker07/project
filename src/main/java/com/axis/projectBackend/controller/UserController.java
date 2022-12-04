package com.axis.projectBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axis.projectBackend.dto.user.SignInDto;
import com.axis.projectBackend.dto.user.SignInResponseDto;
import com.axis.projectBackend.dto.user.SignupDto;
import com.axis.projectBackend.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    // signup

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }


    // signin

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

}