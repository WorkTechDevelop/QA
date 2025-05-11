package ru.worktech.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTaskRequest {
    private String taskId;
    private String title;
    private String description;
    private String priority; // ДОБАВИТЬ ENUM
    private String assignee;
    private String sprintId;
    private int estimation;
    private String code;
    private String status; // ДОБАВИТЬ ENUM

    public UpdateTaskRequest(String taskId, String title, String description, String priority, String assignee, String sprintId, int estimation, String code, String status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignee = assignee;
        this.sprintId = sprintId;
        this.estimation = estimation;
        this.code = code;
        this.status = status;
    }
}
