package ru.worktech.registration_test;

import DataBaseManageServices.DeleteTaskFromDataBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.worktech.models.UpdateTaskRequest;
import ru.worktech.models.CreatedTaskData;
import ru.worktech.steps.TaskSteps;

import static org.apache.http.HttpStatus.SC_OK;

public class UpdateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();
    private final DeleteTaskFromDataBase deleterTask = new DeleteTaskFromDataBase();

    private CreatedTaskData createdTaskData;

    @BeforeMethod
    public void setup() {
        createdTaskData = taskSteps.createTask(
                TaskSteps.getDefaultCreateTask()
                        .title("Test" + System.currentTimeMillis())
                        .priority("MEDIUM")
                        .build()
        ).extractAllTaskData();
    }

    @AfterMethod
    public void teardown() {
        if (createdTaskData != null && createdTaskData.getTaskId() != null) {
            deleterTask.deleteTaskByTaskId(createdTaskData.getTaskId());
        }
    }

    @Test
    public void testSuccessfulUpdateTask() {
        taskSteps.editTask(
                UpdateTaskRequest.builder()
                        .taskId(createdTaskData.getTaskId())
                        .title(createdTaskData.getTitle() + "_updated")
                        .description(createdTaskData.getDescription())
                        .priority(createdTaskData.getPriority())
                        .assignee(createdTaskData.getAssignee())
                        .sprintId(createdTaskData.getSprintId())
                        .projectId(createdTaskData.getProjectId())
                        .taskType(createdTaskData.getTaskType())
                        .estimation(createdTaskData.getEstimation())
                        .code(createdTaskData.getCode())
                        .status(createdTaskData.getStatus())
                        .build()
        ).checkStatusCode(SC_OK);
    }
}