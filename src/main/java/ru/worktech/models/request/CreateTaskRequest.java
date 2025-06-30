package ru.worktech.models.request;

import enums.TaskPriority;
import enums.TaskType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateTaskRequest {

    private String title;
    private String description;
    private String assignee;
    private TaskPriority priority;
    private String projectId;
    private String sprintId;
    private TaskType taskType;
    private Integer estimation;

}