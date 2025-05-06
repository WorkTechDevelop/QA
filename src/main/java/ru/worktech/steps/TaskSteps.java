package ru.worktech.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.worktech.core.AssertableResponse;
import ru.worktech.models.CreateTaskRequest;
import ru.worktech.services.TaskService;

public class TaskSteps {

    private final TaskService taskService = new TaskService();

    @Step("Создать задачу")
    public AssertableResponse createTask(CreateTaskRequest request) {
        Response response = taskService.createTask(request);
        return new AssertableResponse(response);
    }

    @Step("Обновить задачу")
    public AssertableResponse editTask(CreateTaskRequest request) {
        Response response = taskService.editTask(request);
        return new AssertableResponse(response);
    }
}
