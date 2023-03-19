package servise.login.facebook.controller;

import com.servise.login.LoginStrategy;
import com.servise.login.facebook.adapter.FacebookUserToUserAdapter;
import com.servise.login.facebook.controller.FacebookLoginController;
import com.servise.login.facebook.login.FacebookLogin;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class FacebookLoginControllerTest {
    @Mock
    private FacebookLoginController controller;

    private LoginStrategy loginStrategy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = Mockito.mock(FacebookLoginController.class);
        loginStrategy = new FacebookLogin(new FacebookUserToUserAdapter());
    }

    @Test
    public void testFacebookLogin() {
        String expectedUrl = "redirect:facebook-url";
        when(controller.facebookLogin()).thenReturn(expectedUrl);
        String actualUrl = controller.facebookLogin();

        assertEquals(expectedUrl, actualUrl);
    }
}
