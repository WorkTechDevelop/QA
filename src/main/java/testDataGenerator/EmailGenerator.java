package testDataGenerator;

import static java.lang.System.*;
import static java.util.UUID.randomUUID;

public class EmailGenerator {

    public static String generateRandomEmail() {
        return "testUser" + currentTimeMillis() + "@example.com";
    }

    public static String generateRandomPassword() {
        return randomUUID().toString().substring(0, 10);
    }
}