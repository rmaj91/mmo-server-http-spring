package com.maiu.mmoserverhttpspring.commons.dtos;

public interface Constants {
    String USERNAME_VALIDATION_REGEXP = "[a-z0-9]{3,20}";
    String PASSWORD_VALIDATION_REGEXP = "[A-Za-z\\d@$!%*?&]{3,20}";

    int MIN_USERNAME_NAME_LENGTH = 3;
    int MAX_USERNAME_NAME_LENGTH = 20;
    int MIN_PASSWORD_LENGTH = 3;
    int MAX_PASSWORD_LENGTH = 20;
}
