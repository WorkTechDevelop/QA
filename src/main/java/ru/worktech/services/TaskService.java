package ru.worktech.services;

import io.restassured.response.Response;
import ru.worktech.core.BaseApiService;
import ru.worktech.endpoints.Endpoints;

public class TaskService extends BaseApiService {

    public Response createTask(String jsonBody) {
        return getRequestSpec()
                .body(jsonBody)
                .when()
                .post(Endpoints.CREATE_TASK_ENDPOINT);
    }

    public Response editTask(String jsonBody) {
        return getRequestSpec()
                .body(jsonBody)
                .when()
                .put(Endpoints.EDITE_TASK_ENDPOINT);
    }
}
