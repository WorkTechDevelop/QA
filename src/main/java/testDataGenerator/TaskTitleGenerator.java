package testDataGenerator;

import static java.lang.System.*;

public class TaskTitleGenerator {

    public static String generateTaskTitle() {
        return "Test" + currentTimeMillis() + "Task";
    }
}