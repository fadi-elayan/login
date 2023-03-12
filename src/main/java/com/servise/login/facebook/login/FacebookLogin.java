package com.servise.login.facebook.login;

import com.google.inject.Inject;
import com.model.UserResponse;
import com.servise.login.LoginStrategy;
import com.servise.login.facebook.adapter.FacebookUserToUserAdapter;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.FacebookClient;
import lombok.AllArgsConstructor;


import javax.servlet.http.HttpServletRequest;

import static com.constant.LoginConstant.*;

@AllArgsConstructor
public class FacebookLogin implements LoginStrategy {

    private final String fields = "fields";
    private final String id = "id";
    private final String name = "name";
    private final String email = "email";
    private final String comma = ",";
    private final String data = id + comma + name + comma + email;
    @Inject
    private FacebookUserToUserAdapter adapter;

    @Override
    public UserResponse login(HttpServletRequest request) {
        String code = request.getParameter(FACEBOOK_PARAMETER_CODE);
        AccessToken accessToken = new DefaultFacebookClient(Version.LATEST).obtainUserAccessToken(FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URI, code);
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken.getAccessToken(), Version.LATEST);
        User user = facebookClient.fetchObject("me", User.class, Parameter.with(fields, data));
        return adapter.convertFacebookUserToUserAdapter(user);
    }

    @Override
    public String redirect() {
        return "https://www.facebook.com/v12.0/dialog/oauth?client_id=" + FACEBOOK_APP_ID + "&redirect_uri=" + FACEBOOK_REDIRECT_URI + "&scope=email";
    }
}
