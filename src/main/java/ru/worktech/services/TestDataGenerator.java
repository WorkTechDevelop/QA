package ru.worktech.services;

import static java.lang.System.*;

public class TestDataGenerator {

    public static String generateEmail() {
        return "testuser" + currentTimeMillis() + "@example.com"; // генерируем уникальный email
    }
}