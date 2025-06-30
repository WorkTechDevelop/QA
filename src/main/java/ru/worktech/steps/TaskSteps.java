package ru.worktech.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.worktech.core.AssertableResponse;
import ru.worktech.models.TaskDto;
import ru.worktech.models.request.UpdateTaskStatusRequest;
import ru.worktech.models.request.CreateTaskRequest;
import ru.worktech.services.TaskService;

import java.util.Map;

public class TaskSteps {

    private final TaskService taskService = new TaskService();

    @Step("Создать задачу")
    public AssertableResponse createTask(TaskDto request) {
        Response response = taskService.createTask(request);
        return new AssertableResponse(response);
    }

    @Step("Создать задачу")
    public AssertableResponse createTaskMap(Map<String, Object> taskMap) {
        Response response = taskService.createTaskMap(taskMap);
        return new AssertableResponse(response);
    }

    @Step("Создать задачу без авторизации")
    public AssertableResponse createTaskWithOutAuth(TaskDto request) {
        Response response = taskService.createTaskWithoutAuth(request);
        return new AssertableResponse(response);
    }

    @Step("Обновить задачу")
    public AssertableResponse updateTask(TaskDto request) {
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


}