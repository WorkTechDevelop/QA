package testDataGenerator;

import static java.lang.System.*;

public class EmailGenerator {

    public static String generateEmail() {
        return "testUser" + currentTimeMillis() + "@example.com";
    }
}