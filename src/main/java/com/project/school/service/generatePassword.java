package com.project.school.service;

import lombok.Getter;

import java.util.Random;

public class generatePassword {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public enum UserType {
        STUDENT(7),
        TEACHER(11),
        ADMIN(17);

        private final int passwordLength;

        UserType(int passwordLength) {
            this.passwordLength = passwordLength;
        }

        public int getPasswordLength() {
            return passwordLength;
        }
    }

    public String Password(UserType userType) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < userType.getPasswordLength(); i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return password.toString();
    }
}
