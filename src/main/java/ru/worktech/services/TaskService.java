package ru.worktech.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.worktech.core.BaseApiService;
import ru.worktech.endpoints.Endpoints;

public class TaskService extends BaseApiService {

    public Response createTask(String jsonBody) {
        return getSpec(jsonBody).post(Endpoints.CREATE_TASK_ENDPOINT);
    }

    public Response editTask(String jsonBody) {
        return getSpec(jsonBody).put(Endpoints.EDITE_TASK_ENDPOINT);
    }

    private RequestSpecification getSpec(String jsonBody){
        return getRequestSpec()
                .body(jsonBody)
                .when();
    }
}
