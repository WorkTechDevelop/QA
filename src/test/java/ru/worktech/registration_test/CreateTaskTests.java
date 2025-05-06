package ru.worktech.registration_test;

import org.testng.annotations.Test;
import ru.worktech.steps.TaskSteps;

import static org.apache.http.HttpStatus.SC_CREATED;
import static ru.worktech.models.CreateTaskRequest.CreateTaskRequestBuilder;
import static ru.worktech.models.CreateTaskRequest.builder;

public class CreateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();

    @Test
    public void successfulCreateTask() {
    taskSteps.createTask(getDefaultCreateTask()
                    .build())
            .checkStatusCode(SC_CREATED);
    }








    private CreateTaskRequestBuilder getDefaultCreateTask() {
        return builder()
                .title("TestEntity")
                .description("Correct")
                .assignee("830c1f1a-1a10-4a77-b8c0-81d25747bb2f")
                .priority("HIGH")
                .projectId("project-id-929")
                .sprintId("6c17g1c0-5j7f-49vy-ay1a-m98766c6t91")
                .taskType("BUG")
                .estimation("5");

    }
}
