package ru.worktech.models.request;

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
    private Integer estimation;

}