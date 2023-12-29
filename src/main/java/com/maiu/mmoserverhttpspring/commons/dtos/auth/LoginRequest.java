package com.maiu.mmoserverhttpspring.commons.dtos.auth;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String username;
    private String password;

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }
}
