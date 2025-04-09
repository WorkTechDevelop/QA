package org.example.services;

import io.restassured.response.Response;
import org.example.core.BaseApiService;
import org.example.endpoints.Endpoints;

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
