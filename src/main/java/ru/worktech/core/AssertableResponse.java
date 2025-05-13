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
        String taskId = response.jsonPath().getString("taskId");
        String title = response.jsonPath().getString("title");
        String description = response.jsonPath().getString("description");
        String assignee = response.jsonPath().getString("assignee");

        String priorityStr = response.jsonPath().getString("priority");
        TaskPriority priority = TaskPriority.valueOfSafe(priorityStr);

        String sprintId = response.jsonPath().getString("sprintId");
        String projectId = response.jsonPath().getString("projectId");

        String taskTypeStr = response.jsonPath().getString("taskType");
        TaskType taskType = TaskType.valueOfSafe(taskTypeStr);

        String estimationStr = response.jsonPath().getString("estimation");
        Integer estimation = parseIntSafe(estimationStr);

        String code = response.jsonPath().getString("code");
        String statusStr = response.jsonPath().getString("status");
        TaskStatus status = TaskStatus.valueOfSafe(statusStr);

        return new CreatedTaskData(
                taskId,
                title,
                description,
                assignee,
                priority,
                projectId,
                sprintId,
                taskType,
                estimation,
                code,
                status
        );
    }

    private Integer parseIntSafe(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}