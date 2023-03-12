package com.servise.login.facebook.adapter;

import com.restfb.types.User;
import com.model.UserResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

@Component
@Singleton
@ComponentScan
public class FacebookUserToUserAdapter {

    public UserResponse convertFacebookUserToUserAdapter(User facebookUser){
        UserResponse userResponse =  new UserResponse(facebookUser.getName(), facebookUser.getEmail());
        return userResponse;
    }
}
