package ru.worktech.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTaskRequest {

    private String title;
    private String description;
    private String assignee;
    private String priority;
    private String projectId;
    private String sprintId;
    private String taskType;
    private String estimation;

    public CreateTaskRequest(String title, String description, String assignee, String priority, String projectId, String sprintId, String taskType, String estimation) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.projectId = projectId;
        this.sprintId = sprintId;
        this.taskType = taskType;
        this.estimation = estimation;
    }
}