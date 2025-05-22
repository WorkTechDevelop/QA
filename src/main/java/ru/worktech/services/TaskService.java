package ru.worktech.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.worktech.core.BaseApiService;
import ru.worktech.models.UpdateTaskStatusRequest;
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

    public Response updateTaskStatus (UpdateTaskStatusRequest request) {
        return getSpec(request).put(UPDATE_TASK_STATUS.getAddress());
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

    private RequestSpecification getSpec(UpdateTaskStatusRequest request) {
        return getRequestSpec()
                .body(request)
                .when();
    }
}