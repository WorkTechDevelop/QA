package ru.worktech.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.worktech.core.BaseApiService;
import ru.worktech.models.CreateTaskRequest;
import ru.worktech.models.UpdateTaskRequest;

import static ru.worktech.endpoints.Endpoints.CREATE_TASK_ENDPOINT;
import static ru.worktech.endpoints.Endpoints.EDITE_TASK_ENDPOINT;

public class TaskService extends BaseApiService {

    public Response createTask(CreateTaskRequest request) {
        return getSpec(request)
                .post(CREATE_TASK_ENDPOINT);
    }

    public Response editTask(UpdateTaskRequest request) {
        return getSpec(request).put(EDITE_TASK_ENDPOINT);
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
