package com.servise.login.google.login;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential.Builder;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.model.Userinfo;
import com.google.inject.Inject;
import com.model.UserResponse;
import com.servise.login.LoginStrategy;
import com.servise.login.google.adapter.GoogleUserToUserAdapter;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;


import static com.constant.LoginConstant.*;
@AllArgsConstructor
public class GoogleLogin implements LoginStrategy {

    @Inject
    private GoogleUserToUserAdapter googleUserToUserAdapter;
    @Override
    public UserResponse login(HttpServletRequest request)  {
        GoogleAuthorizationCodeFlow flow = null;
        try {
            flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), GOOGLE_CLIENT_ID, GOOGLE_SECRET_KEY, GOOGLE_SCOPES).build();
            GoogleTokenResponse tokenResponse = flow.newTokenRequest(request.getParameter(CODE_PARAMETER)).setRedirectUri(GOOGLE_REDIRECT_URI_CALLBACK).execute();
            GoogleCredential credential = new Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport()).setJsonFactory(JacksonFactory.getDefaultInstance()).setClientSecrets(GOOGLE_CLIENT_ID, GOOGLE_SECRET_KEY).build().setAccessToken(tokenResponse.getAccessToken()).setRefreshToken(tokenResponse.getRefreshToken());
            Oauth2 oauth2 = new Oauth2.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).build();
            Userinfo userInfo = oauth2.userinfo().get().execute();
            System.out.println(userInfo.toString());
            return googleUserToUserAdapter.convertGoogleUserToUserAdapter(userInfo);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
