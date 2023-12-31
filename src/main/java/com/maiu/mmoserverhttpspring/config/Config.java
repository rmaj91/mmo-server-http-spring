package com.maiu.mmoserverhttpspring.config;

public interface Config {
    String HTTP_PREFIX = "/v1/api";

    //RESOURCES
    String HTTP_ACCOUNTS_RESOURCE = "/accounts";
    String HTTP_AUTH_RESOURCE = "/auth";
    String HTTP_AUTH_LOGIN_RESOURCE = "/login";
    String HTTP_AUTH_LOGOUT_RESOURCE = "/logout";
    String HTTP_CHARACTERS_RESOURCE = "/characters";
    String HTTP_ZONES_RESOURCE = "/zones";

    //PATH PREFIXES
    String HTTP_ACCOUNTS_PREFIX = HTTP_PREFIX + HTTP_ACCOUNTS_RESOURCE;
    String HTTP_AUTH_PREFIX = HTTP_PREFIX + HTTP_AUTH_RESOURCE;
    String HTTP_CHARACTERS_PREFIX = HTTP_PREFIX + HTTP_CHARACTERS_RESOURCE;
    String HTTP_ZONES_PREFIX = HTTP_PREFIX + HTTP_ZONES_RESOURCE;
}
