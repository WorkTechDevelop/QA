package ru.worktech.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.worktech.core.BaseApiService;
import ru.worktech.models.request.CreateTaskRequest;
import ru.worktech.models.request.UpdateTaskRequest;

import static ru.worktech.endpoints.ApiEndpoints.*;

public class TaskService extends BaseApiService {

    public Response createTask(CreateTaskRequest request) {
        return getSpec(request)
                .post(CREATE_TASK_ENDPOINT.getAddress());
    }

    public Response editTask(UpdateTaskRequest request) {
        return getSpec(request).put(EDITE_TASK_ENDPOINT.getAddress());
    }

    public Response getTaskByTaskCode(String taskCode) {
        return getRequestSpec()
                .when()
                .get(GET_TASK_BY_ID_ENDPOINT.resolve(taskCode));
    }

    private RequestSpecification getSpec(CreateTaskRequest request) {
        return getRequestSpec()
                .body(request)
                .when();
    }

    private RequestSpecification getSpec(UpdateTaskRequest request) {
        return getRequestSpec()
                .body(request)
                .when();
    }
}