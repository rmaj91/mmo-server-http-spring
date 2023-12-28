package com.maiu.mmoserverhttpspring.config;

public interface Config {
    String HTTP_PREFIX = "/v1/api";
    String HTTP_ACCOUNTS_RESOURCE = "/accounts";
    String HTTP_ACCOUNTS_PREFIX = HTTP_PREFIX + HTTP_ACCOUNTS_RESOURCE;
}
