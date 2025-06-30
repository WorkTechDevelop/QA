package ru.worktech.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ru.worktech.models.request.CreateTaskRequest;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class TaskDto {

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
    private String TaskCode;

}