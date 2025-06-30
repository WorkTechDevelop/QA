package ru.worktech.services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.worktech.core.BaseApiService;
import ru.worktech.models.TaskDto;
import ru.worktech.models.request.UpdateTaskStatusRequest;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.worktech.endpoints.ApiEndpoints.*;

public class TaskService extends BaseApiService {

    public Response createTask(TaskDto request) {
        return getSpec(request)
                .post(CREATE_TASK_ENDPOINT.getAddress());
    }

    public Response createTaskMap(Map<String, Object> taskMap) {
        return getSpec(taskMap)
                .post(CREATE_TASK_ENDPOINT.getAddress());
    }

    public Response createTaskWithoutAuth(TaskDto request) {
        return getSpecWithOutAuth(request)
                .post(CREATE_TASK_ENDPOINT.getAddress());
    }

    public Response editTask(TaskDto request) {
        return getSpec(request).put(EDITE_TASK_ENDPOINT.getAddress());
    }

    public Response updateTaskStatus(UpdateTaskStatusRequest request) {
        return getSpec(request).put(UPDATE_TASK_STATUS.getAddress());
    }

    public Response updateTaskStatusWithOutAuth(UpdateTaskStatusRequest request) {
        return getSpecWithOutAuth(request)
                .post(UPDATE_TASK_STATUS.getAddress());
    }

    private RequestSpecification getSpec(Object request) {
        return getRequestSpec()
                .body(request)
                .when();
    }

    private RequestSpecification getSpecWithOutAuth(Object request) {
        return getRequestSpecWithOutAuth()
                .body(request)
                .when();
    }
}