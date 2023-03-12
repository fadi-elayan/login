package com.servise.login.google.login;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential.Builder;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.model.Userinfo;
import com.model.UserResponse;
import com.servise.login.LoginStrategy;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


import static com.constant.LoginConstant.*;

public class GoogleLogin implements LoginStrategy {
    private static final List<String> SCOPES = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile", "https://www.googleapis.com/auth/userinfo.email");

    @Override
    public UserResponse login(HttpServletRequest request)  {
        // Create the authorization flow
        GoogleAuthorizationCodeFlow flow = null;
        try {
            flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), GOOGLE_CLIENT_ID, GOOGLE_SECRET_KEY, SCOPES).build();
            GoogleTokenResponse tokenResponse = flow.newTokenRequest(request.getParameter("code")).setRedirectUri(GOOGLE_REDIRECT_URI_CALLBACK).execute();

            // Use the access token to authenticate with Google
            GoogleCredential credential = new Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport()).setJsonFactory(JacksonFactory.getDefaultInstance()).setClientSecrets(GOOGLE_CLIENT_ID, GOOGLE_SECRET_KEY).build().setAccessToken(tokenResponse.getAccessToken()).setRefreshToken(tokenResponse.getRefreshToken());

            // Use the authenticated credential to make API requests
            Oauth2 oauth2 = new Oauth2.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).build();
            Userinfo userInfo = oauth2.userinfo().get().execute();
            System.out.println("Welcome, " + userInfo.getName());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




        return null;
    }

    @Override
    public String redirect() {
        return GOOGLE_REDIRECT + generateStateToken();
    }

    private String generateStateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] stateBytes = new byte[32];
        secureRandom.nextBytes(stateBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(stateBytes);
    }
}
