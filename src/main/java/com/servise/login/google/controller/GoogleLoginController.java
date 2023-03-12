package com.servise.login.google.controller;

import com.model.UserResponse;
import com.servise.login.LoginStrategy;
import com.servise.login.google.login.GoogleLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoogleLoginController {

    private LoginStrategy loginStrategy = new GoogleLogin();

    @GetMapping("/google-login")
    public String GoogleLogin() {
        return "redirect:" + loginStrategy.redirect();
    }

    @GetMapping("/google-login/callback")
    public ResponseEntity<UserResponse> GoogleLoginCallback(HttpServletRequest request) {
        UserResponse userResponse = loginStrategy.login(request);
        return new ResponseEntity(userResponse, HttpStatus.OK);
    }
}