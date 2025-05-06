package ru.worktech.services;

import static java.lang.System.*;

public class TestDataGenerator {

    public static String generateEmail() {
        return "testUser" + currentTimeMillis() + "@example.com";
    }
}