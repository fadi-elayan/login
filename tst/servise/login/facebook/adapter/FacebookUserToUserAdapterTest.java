package servise.login.facebook.adapter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.model.UserResponse;
import com.restfb.types.User;
import com.servise.login.facebook.adapter.FacebookUserToUserAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import servise.utility.TestModule;
import servise.utility.Utility;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FacebookUserToUserAdapterTest {

    private FacebookUserToUserAdapter adapter;

    private Utility utility = new Utility();

    @Before
    public void setup() {
        Injector injector = Guice.createInjector(new TestModule());
        utility = injector.getInstance(Utility.class);
        adapter = injector.getInstance(FacebookUserToUserAdapter.class);
    }

    @Test
    public void testConvertFacebookUserToUserAdapter() {
        User facebookUser = utility.createFacebookUser();
        UserResponse userResponse = adapter.convertFacebookUserToUserAdapter(facebookUser);
        assertEquals(facebookUser.getName(), userResponse.getName());
        assertEquals(facebookUser.getEmail(), userResponse.getEmail());
    }

    @Test
    public void testConvertEmptyFacebookUserToUserAdapter() {
        User facebookUser = utility.createEmptyFacebookUser();
        UserResponse userResponse = adapter.convertFacebookUserToUserAdapter(facebookUser);
        assertEquals(facebookUser.getName(), userResponse.getName());
        assertEquals(facebookUser.getEmail(), userResponse.getEmail());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConvertNullFacebookUserToUserAdapter() {
        adapter.convertFacebookUserToUserAdapter(null);
    }

}
