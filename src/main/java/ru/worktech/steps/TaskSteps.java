package ru.worktech.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.worktech.core.AssertableResponse;
import ru.worktech.models.CreateTaskRequest;
import ru.worktech.models.UpdateTaskRequest;
import ru.worktech.services.TaskService;

public class TaskSteps {

    private final TaskService taskService = new TaskService();

    @Step("Создать задачу")
    public AssertableResponse createTask(CreateTaskRequest request) {
        Response response = taskService.createTask(request);
        return new AssertableResponse(response);
    }

    @Step("Обновить задачу")
    public AssertableResponse editTask(UpdateTaskRequest request) {
        Response response = taskService.editTask(request);
        return new AssertableResponse(response);
    }

    public static CreateTaskRequest.CreateTaskRequestBuilder getDefaultCreateTask() {
        return CreateTaskRequest.builder()
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
