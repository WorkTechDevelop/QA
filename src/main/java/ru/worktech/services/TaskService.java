package ru.worktech.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.worktech.core.BaseApiService;

import static ru.worktech.endpoints.Endpoints.CREATE_TASK_ENDPOINT;
import static ru.worktech.endpoints.Endpoints.EDITE_TASK_ENDPOINT;

public class TaskService extends BaseApiService {

    public Response createTask(String jsonBody) {
        return getSpec(jsonBody).post(CREATE_TASK_ENDPOINT);
    }

    public Response editTask(String jsonBody) {
        return getSpec(jsonBody).put(EDITE_TASK_ENDPOINT);
    }

    private RequestSpecification getSpec(String jsonBody) {
        return getRequestSpec()
                .body(jsonBody)
                .when();
    }
}
