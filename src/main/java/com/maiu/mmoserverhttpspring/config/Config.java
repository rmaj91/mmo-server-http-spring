package com.maiu.mmoserverhttpspring.config;

public interface Config {
    String HTTP_PREFIX = "/v1/api";
    String HTTP_ACCOUNTS_RESOURCE = "/accounts";
    String HTTP_AUTH_RESOURCE = "/auth";
    String HTTP_AUTH_LOGIN_RESOURCE = "/login";
    String HTTP_CHARACTERS_RESOURCE = "/characters";
    String HTTP_ACCOUNTS_PREFIX = HTTP_PREFIX + HTTP_ACCOUNTS_RESOURCE;
    String HTTP_AUTH_PREFIX = HTTP_PREFIX + HTTP_AUTH_RESOURCE;
    String HTTP_CHARACTERS_PREFIX = HTTP_PREFIX + HTTP_CHARACTERS_RESOURCE;
}
