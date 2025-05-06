package ru.worktech.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.worktech.core.BaseApiService;
import ru.worktech.models.CreateTaskRequest;
import ru.worktech.models.UpdateTaskStatusRequest;

import static ru.worktech.endpoints.Endpoints.*;

public class TaskService extends BaseApiService {

    public Response createTask(CreateTaskRequest request) {
        return getSpec(request).post(CREATE_TASK_ENDPOINT);
    }

    public Response editTask(CreateTaskRequest request) {
        return getSpec(request).put(EDITE_TASK_ENDPOINT);
    }

    public Response updateTask(UpdateTaskStatusRequest request) {return getSpec(request).put(UPDATE_TASK_STATUS_ENDPOINT);}

    private RequestSpecification getSpec(CreateTaskRequest request) {
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
