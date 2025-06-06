package testDataGenerator;

import static java.lang.System.currentTimeMillis;
import static java.util.UUID.randomUUID;

public class TestDataGenerator {

    public static String generateRandomEmail() {
        return "testUser" + currentTimeMillis() + "@example.com";
    }

    public static String generateRandomPassword() {
        return randomUUID().toString().substring(0, 10);
    }

    public static String generateTaskTitle() {
        return "Test" + currentTimeMillis() + "Task";
    }
}