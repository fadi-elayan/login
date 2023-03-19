package com.configuration.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.UserResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@ComponentScan
public class UserResponseConverter implements HttpMessageConverter<UserResponse> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return UserResponse.class.isAssignableFrom(clazz);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.APPLICATION_JSON);
    }

    @Override
    public UserResponse read(Class<? extends UserResponse> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        throw new HttpMessageNotReadableException("Not supported");
    }

    @Override
    public void write(UserResponse userResponse, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(userResponse);
        outputMessage.getBody().write(json.getBytes());
    }
}

