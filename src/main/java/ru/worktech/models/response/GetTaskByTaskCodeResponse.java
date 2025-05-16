package ru.worktech.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTaskByTaskCodeResponse {
    private String taskId;
    private String title;
    private String description;
    private TaskPriority priority;
    private String assignee;
    private String projectId;
    private String sprintId;
    private TaskType taskType;
    private Integer estimation;
    private TaskStatus status;
    private String code;

    public GetTaskByTaskCodeResponse(String taskId, String title, String description,
                                     TaskPriority priority, String assignee, String projectId,
                                     String sprintId, TaskType taskType,
                                     Integer estimation, TaskStatus status, String code) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignee = assignee;
        this.projectId = projectId;
        this.sprintId = sprintId;
        this.taskType = taskType;
        this.estimation = estimation;
        this.status = status;
        this.code = code;
    }
}