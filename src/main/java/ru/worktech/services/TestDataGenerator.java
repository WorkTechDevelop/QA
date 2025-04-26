package ru.worktech.services;

public class TestDataGenerator {

    public static String generateEmail() {
        return "testuser" + System.currentTimeMillis() + "@example.com"; // генерируем уникальный email
    }
}


