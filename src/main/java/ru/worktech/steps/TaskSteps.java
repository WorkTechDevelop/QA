package ru.worktech.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.worktech.core.AssertableResponse;
import ru.worktech.models.CreateTaskRequest;
import ru.worktech.services.TaskService;

public class TaskSteps {

    private final TaskService taskService = new TaskService();

    @Step("Создать задачу")
    public AssertableResponse createTask(CreateTaskRequest task) {
        Response response = taskService.createTask(task);
        return new AssertableResponse(response);
    }

    @Step("Обновить задачу")
    public AssertableResponse editTask(String jsonBody) {
        Response response = taskService.editTask(jsonBody);
        return new AssertableResponse(response);
    }
}
