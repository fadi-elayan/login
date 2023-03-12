package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class UserResponse {
    private String name;
    private String email;
    public UserResponse(){}
}
