package ru.worktech.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.worktech.core.BaseApiService;
import ru.worktech.endpoints.Endpoints;
import ru.worktech.models.CreateTaskRequest;

import static ru.worktech.endpoints.Endpoints.CREATE_TASK_ENDPOINT;
import static ru.worktech.endpoints.Endpoints.REGISTRATION_ENDPOINT;

public class TaskService extends BaseApiService {

    public Response createTask(CreateTaskRequest task) {
        return getRequestSpec()
                .body(task)
                .when()
                .post(CREATE_TASK_ENDPOINT);
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
