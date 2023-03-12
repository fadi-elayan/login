package com.servise.login.facebook.controller;

import com.model.UserResponse;
import com.servise.login.LoginStrategy;
import com.servise.login.facebook.adapter.FacebookUserToUserAdapter;
import com.servise.login.facebook.login.FacebookLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

import static com.constant.ViewConstant.HELLO_VIEW;

@Controller
public class FacebookLoginController {

    private LoginStrategy loginStrategy = new FacebookLogin(new FacebookUserToUserAdapter());

    @GetMapping("/facebook-login")
    public String facebookLogin() {
        return "redirect:" + loginStrategy.redirect();
    }

    @GetMapping("/facebook-login/callback")
    public ResponseEntity<UserResponse> facebookLoginCallback(HttpServletRequest request) {
        UserResponse userResponse = loginStrategy.login(request);
        return new ResponseEntity(userResponse, HttpStatus.OK);
    }
}