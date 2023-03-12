package com.servise.login;
import com.model.UserResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;

public interface LoginStrategy {
    UserResponse login(HttpServletRequest request);
     String redirect();
}
