package ru.worktech.models;

import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;

public class CreatedTaskData {
    private final String taskId;
    private final String title;
    private final String description;
    private final String assignee;
    private final TaskPriority priority;
    private final String projectId;
    private final String sprintId;
    private final TaskType taskType;
    private final int estimation;
    private final String code;
    private final TaskStatus status;

    public CreatedTaskData(String taskId, String title, String description, String assignee, TaskPriority priority,
                           String projectId, String sprintId, TaskType taskType, int estimation, String code,
                           TaskStatus status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.projectId = projectId;
        this.sprintId = sprintId;
        this.taskType = taskType;
        this.estimation = estimation;
        this.code = code;
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignee() {
        return assignee;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getSprintId() {
        return sprintId;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public int getEstimation() {
        return estimation;
    }

    public String getCode() {
        return code;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
