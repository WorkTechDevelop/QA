package testDataGenerator;

import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;
import ru.worktech.models.TaskDto;
import ru.worktech.models.request.RegistrationRequest;
import ru.worktech.models.request.UpdateTaskStatusRequest;

import java.util.HashMap;
import java.util.Map;

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

    public static TaskDto getDefaultCreateTask() {
        return new TaskDto()
                .setProjectId("17565a09-5b2d-4edd-acf0-d69b3ce57b9d")
                .setTitle("TestEntity")
                .setDescription("Correct")
                .setAssignee("37563a09-5b2d-4edd-acf0-d69b3ce57b9d")
                .setPriority(TaskPriority.LOW)
                .setProjectId("17565a09-5b2d-4edd-acf0-d69b3ce57b9d")
                .setSprintId("24265a09-5b2d-4edd-acf0-d69b3ce57b9d")
                .setTaskType(TaskType.TASK)
                .setEstimation(5);
    }

    public static Map<String, Object> getDefaultCreateTaskRequestMap() {
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("title", "TestEntity");
        taskMap.put("description", "Correct");
        taskMap.put("assignee", "37563a09-5b2d-4edd-acf0-d69b3ce57b9d");
        taskMap.put("priority", "HIGH");
        taskMap.put("projectId", "17565a09-5b2d-4edd-acf0-d69b3ce57b9d");
        taskMap.put("sprintId", "24265a09-5b2d-4edd-acf0-d69b3ce57b9d");
        taskMap.put("taskType", "BUG");
        taskMap.put("estimation", 5);
        return taskMap;
    }

    public static TaskDto getDefaultUpdateTask() {
        return new TaskDto()
                .setTaskId("")
                .setTaskCode("")
                .setTaskType(TaskType.BUG)
                .setStatus(TaskStatus.TODO)
                .setDescription("Opisanie")
                .setPriority(TaskPriority.LOW)
                .setAssignee("")
                .setTitle("zagolovok")
                .setEstimation(1);
    }

    public static RegistrationRequest getDefaultRegistration() {
        return new RegistrationRequest()
                .setEmail("default@.com")
                .setPassword("defaultPassword")
                .setConfirmPassword("defaultPassword")
                .setLastName("defaultLastName")
                .setFirstName("defaultFirstName")
                .setMiddleName("defaultMiddleName")
                .setPhone("+79991001010")
                .setBirthDate("2020-01-01")
                .setGender("MALE");
    }

    public static Map<String, Object> getDefaultRegistrationMap() {
        Map<String, Object> registrationMap = new HashMap<>();
        registrationMap.put("email", "default@.com");
        registrationMap.put("password", "defaultPassword");
        registrationMap.put("confirmPassword", "defaultPassword");
        registrationMap.put("lastName", "defaultLastName");
        registrationMap.put("firstName", "defaultFirstName");
        registrationMap.put("middleName", "defaultMiddleName");
        registrationMap.put("phone", "+79991001010");
        registrationMap.put("birthDate", "01-01-1971");
        registrationMap.put("gender", "MALE");
        return registrationMap;
    }

    public static UpdateTaskStatusRequest getDefaultUpdateTaskStatus(){
        return new UpdateTaskStatusRequest()
                .setStatus(1)
                .setCode("");
    }
}
