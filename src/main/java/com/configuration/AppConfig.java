package com.configuration;

import com.servise.login.facebook.adapter.FacebookUserToUserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.servise.login.facebook.adapter")
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public FacebookUserToUserAdapter facebookUserToUserAdapter() {
        FacebookUserToUserAdapter adapter = new FacebookUserToUserAdapter();
        return adapter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converters.add(converter);
    }

}
