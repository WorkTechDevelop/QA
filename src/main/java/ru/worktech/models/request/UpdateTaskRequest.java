package ru.worktech.models.request;

import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTaskRequest {
    private String id;
    private String title;
    private String description;
    private TaskPriority priority;
    private String assignee;
    private String sprintId;
    private Integer estimation;
    private String projectId;
    private TaskType taskType;
    private String code;
    private TaskStatus status;
}