package servise.utility;

import com.restfb.types.User;

public class Utility {
    private final String EMAIL = "test@example.com";
    private final String USER_NAME = "test@example.com";

    public User createFacebookUser(){
        User user = new User();
        user.setEmail(EMAIL);
        user.setName(USER_NAME);
        return user;
    }

    public User createEmptyFacebookUser(){
        return new User();
    }
}
