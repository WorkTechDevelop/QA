package ru.worktech.core;

import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;
import io.restassured.response.Response;
import ru.worktech.models.CreatedTaskData;

import static org.hamcrest.Matchers.equalTo;

public class AssertableResponse {

    private final Response response;

    public AssertableResponse(Response response) {
        this.response = response;
    }

    public AssertableResponse checkStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
        return this;
    }

    public AssertableResponse checkBodyFieldEquals(String field, Object value) {
        response.then().body(field, equalTo(value));
        return this;
    }

    public String extractTaskId() {
        return response.jsonPath().getString("taskId");
    }

    public CreatedTaskData extractAllTaskData() {
        return new CreatedTaskData(
                response.jsonPath().getString("taskId"),
                response.jsonPath().getString("title"),
                response.jsonPath().getString("description"),
                response.jsonPath().getString("assignee"),
                TaskPriority.valueOf(response.jsonPath().getString("priority")),
                response.jsonPath().getString("sprintId"),
                response.jsonPath().getString("projectId"),
                TaskType.valueOf(response.jsonPath().getString("taskType")),
                Integer.parseInt(response.jsonPath().getString("estimation")),
                response.jsonPath().getString("code"),
                TaskStatus.valueOf(response.jsonPath().getString("status"))
        );
    }
}