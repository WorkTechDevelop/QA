package ru.worktech.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.worktech.core.AssertableResponse;
import ru.worktech.models.UpdateTaskStatusRequest;
import ru.worktech.models.request.CreateTaskRequest;
import ru.worktech.models.request.CreateTaskRequest.CreateTaskRequestBuilder;
import ru.worktech.models.request.UpdateTaskRequest;
import ru.worktech.services.TaskService;

import static ru.worktech.models.request.CreateTaskRequest.*;

public class TaskSteps {

    private final TaskService taskService = new TaskService();

    @Step("Создать задачу")
    public AssertableResponse createTask(CreateTaskRequest request) {
        Response response = taskService.createTask(request);
        return new AssertableResponse(response);
    }

    @Step("Создать задачу без авторизации")
    public AssertableResponse createTaskWithOutAuth(CreateTaskRequest request) {
        Response response = taskService.createTaskWithoutAuth(request);
        return new AssertableResponse(response);
    }

    @Step("Обновить задачу")
    public AssertableResponse updateTask(UpdateTaskRequest request) {
        Response response = taskService.editTask(request);
        return new AssertableResponse(response);
    }

    @Step("Обновить статус задачи")
    public AssertableResponse updateTaskStatus(UpdateTaskStatusRequest request) {
        Response response = taskService.updateTaskStatus(request);
        return new AssertableResponse(response);
    }

    @Step("Обновить статус задачи")
    public AssertableResponse updateTaskStatusWithOutAuth(UpdateTaskStatusRequest request) {
        Response response = taskService.updateTaskStatusWithOutAuth(request);
        return new AssertableResponse(response);
    }

    public static CreateTaskRequestBuilder getDefaultCreateTask() {
        return builder()
                .title("TestEntity")
                .description("Correct")
                .assignee("a4069488-7d8f-40bc-80e1-025322316901")
                .priority("HIGH")
                .projectId("project-123")
                .sprintId("0001")
                .taskType("BUG")
                .estimation(5);
    }
}