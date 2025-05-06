package ru.worktech.registration_test;

import org.testng.annotations.Test;
import ru.worktech.steps.TaskSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static ru.worktech.models.CreateTaskRequest.CreateTaskRequestBuilder;
import static ru.worktech.models.CreateTaskRequest.builder;

public class CreateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();

    @Test
    public void successfulCreateTask() {
    taskSteps.createTask(getDefaultCreateTask().build())
            .checkStatusCode(SC_OK);
    }








    private CreateTaskRequestBuilder getDefaultCreateTask() {
        return builder()
                .title("TestEntity")
                .description("<script>alert('XSS')</script>")
                .assignee("830c1f1a-1a10-4a77-b8c0-81d25747bb2f")
                .priority("HIGH")
                .projectId("project-id-456")
                .sprintId("4b17f4c0-5c4f-49be-bb5a-a55676c3c15")
                .taskType("BUG")
                .estimation("5");

    }
}
