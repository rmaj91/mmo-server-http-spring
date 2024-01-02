package com.maiu.mmoserverhttpspring.guest;

import org.springframework.stereotype.Component;

import java.util.Random;

import static com.maiu.mmoserverhttpspring.commons.dtos.Constants.MAX_CHARACTER_NAME_LENGTH;
import static com.maiu.mmoserverhttpspring.commons.dtos.Constants.MAX_USERNAME_NAME_LENGTH;

@Component
public class RandomNameGenerator {

    private static final String FOR_CHARACTER_NAME = "abcdefghijklmnopqrstuvwxyz";
    private static final String FOR_ACCOUNT_NAME = "abcdefghijklmnopqrstuvwxyz123456789@$!%*?&";
    private static final Random RANDOM = new Random();

    public String generateRandomCharacterName() {
        StringBuilder randomString = new StringBuilder(MAX_CHARACTER_NAME_LENGTH);
        for (int i = 0; i < MAX_CHARACTER_NAME_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(FOR_CHARACTER_NAME.length());
            char randomChar = FOR_CHARACTER_NAME.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.substring(0, 1).toUpperCase() + randomString.substring(1, MAX_CHARACTER_NAME_LENGTH);
    }

    public String generateRandomUsername() {
        StringBuilder randomString = new StringBuilder(MAX_USERNAME_NAME_LENGTH);
        for (int i = 0; i < MAX_USERNAME_NAME_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(FOR_CHARACTER_NAME.length());
            char randomChar = FOR_CHARACTER_NAME.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString().toLowerCase();
    }
}