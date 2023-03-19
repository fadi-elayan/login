package com.configuration;

import com.configuration.converter.UserResponseConverter;
import com.servise.login.facebook.adapter.FacebookUserToUserAdapter;
import com.servise.login.google.adapter.GoogleUserToUserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.configuration")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public FacebookUserToUserAdapter facebookUserToUserAdapter() {
        return new FacebookUserToUserAdapter();
    }

    @Bean
    public GoogleUserToUserAdapter googleUserToUserAdapter() {
        return new GoogleUserToUserAdapter();
    }
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new UserResponseConverter());
    }

}
