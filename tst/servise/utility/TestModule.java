package servise.utility;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.servise.login.facebook.adapter.FacebookUserToUserAdapter;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Utility.class).in(Singleton.class);
        bind(FacebookUserToUserAdapter.class).in(Singleton.class);
    }
}
