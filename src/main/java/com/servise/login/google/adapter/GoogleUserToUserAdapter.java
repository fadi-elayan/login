package com.servise.login.google.adapter;

import com.google.api.services.oauth2.model.Userinfo;
import com.google.inject.Singleton;
import com.model.UserResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@Singleton
@ComponentScan
public class GoogleUserToUserAdapter {
    public UserResponse convertGoogleUserToUserAdapter(Userinfo googleUser){
        if (googleUser == null) {
            throw new IllegalArgumentException("googleUser argument cannot be null");
        }
        return new UserResponse(googleUser.getName(), googleUser.getEmail());
    }
}
