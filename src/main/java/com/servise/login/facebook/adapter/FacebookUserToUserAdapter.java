package com.servise.login.facebook.adapter;

import com.google.inject.AbstractModule;
import com.restfb.types.User;
import com.model.UserResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

@Component
@Singleton
@ComponentScan
public class FacebookUserToUserAdapter extends AbstractModule {

    public UserResponse convertFacebookUserToUserAdapter(User facebookUser){
        if (facebookUser == null) {
            throw new IllegalArgumentException("facebookUser argument cannot be null");
        }
        return new UserResponse(facebookUser.getName(), facebookUser.getEmail());
    }
}
