package ru.worktech.core;

import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;
import io.restassured.response.Response;
import ru.worktech.models.response.GetTaskByTaskCodeResponse;

public class AssertableResponse {

    private final Response response;

    public AssertableResponse(Response response) {
        this.response = response;
    }

    public void checkStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public GetTaskByTaskCodeResponse extractAllTaskData() {
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

        return new GetTaskByTaskCodeResponse(
                taskId,
                title,
                description,
                priority,
                assignee,
                projectId,
                sprintId,
                taskType,
                estimation,
                status,
                code
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