package com.constant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginConstant {
    public static final String FACEBOOK_APP_ID = "1197741954187269";
    public static final String FACEBOOK_APP_SECRET = "b671d961dea5bd245caffdd5181e3420";
    public static final String FACEBOOK_REDIRECT_URI = "http://localhost:8080/Login/facebook-login/callback";
    public static final String FACEBOOK_PARAMETER_CODE = "code";
    public static final String FACEBOOK_ACCESS_TOKEN = "EAARBVwe2QAUBAOKGIP8o4JFds4vg72ME6ngHVtGdOlVtpvJGSgYKLJl8ZA1OkuuBncFwFp3SlZA4384mwllq3u2ZC72BRgJoLoVcOd4swUS9QRcuDfk6Vj9bXVNleUO24wRvI5Pkho0cyZBggZCzXatf4yLMZAoMEaEZBexTE4NnLYTqrzX31mkLSFQ5MnL0KAZD";


    public static final String GOOGLE_SECRET_KEY = "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";
    public static final String GOOGLE_CLIENT_ID = "43335671369-9gc54i78vjqjreftld8e0k7qm88t0fuv.apps.googleusercontent.com";
    public static final String GOOGLE_REDIRECT_URI_CALLBACK = "http://localhost:8080/Login/google-login/callback";
    public static final String GOOGLE_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
    public static final String GOOGLE_REDIRECT = "https://accounts.google.com/o/oauth2/v2/auth?response_type=code&" + "client_id=" + GOOGLE_CLIENT_ID + "&redirect_uri=" + GOOGLE_REDIRECT_URI_CALLBACK + "&scope=" + GOOGLE_SCOPE + "&state=";

}
