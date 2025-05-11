package ru.worktech.registration_test;

import DataBaseManageServices.DeleteTaskFromDataBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.worktech.models.UpdateTaskRequest;
import ru.worktech.steps.TaskSteps;

import static org.apache.http.HttpStatus.SC_CREATED;

public class UpdateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();
    CreateTaskTests createTaskForTest = new CreateTaskTests();
    DeleteTaskFromDataBase deleterTask = new DeleteTaskFromDataBase();

    @BeforeMethod
    public void successfulCreateTaskForUpdateTest() {
    createTaskForTest.successfulCreateTask();
    }

    @AfterMethod
    public void deleteTaskFromDataBase() {
    deleterTask.deleteTaskByTitle("TestEntity123");
    }

    @Test
    public void testSuccessfulUpdateTask() {
        taskSteps.editTask(getDefaultUpdateTask()
                        .build())
                .checkStatusCode(SC_CREATED);
    }

    private UpdateTaskRequest.UpdateTaskRequestBuilder getDefaultUpdateTask() {
        return UpdateTaskRequest.builder()
                .taskId("123")
                .title("TestEntity123")
                .description("Correct")
                .priority("HIGH")
                .assignee("user123")
                .sprintId("sprint-456")
                .estimation(5)
                .status("OPEN");
    }
}