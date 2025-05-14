package ru.worktech.registration_test;

import DataBaseManageServices.DeleteTaskFromDataBase;
import enums.TaskPriority;
import enums.TaskStatus;
import enums.TaskType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.worktech.models.UpdateTaskRequest;
import ru.worktech.models.CreatedTaskData;
import ru.worktech.steps.TaskSteps;

public class UpdateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();
    private final DeleteTaskFromDataBase deleterTask = new DeleteTaskFromDataBase();

    private CreatedTaskData createdTaskData;

    String validSprintId = "6c17g1c0-5j7f-49vy-ay1a-m98766c6t91";
    String validProjectId = "project-id-929";
    String validAssignee = "830c1f1a-1a10-4a77-b8c0-81d25747bb2f";
    String validCode = "TPO-0057";
    TaskType validTaskType = TaskType.BUG;
    TaskPriority validPriority = TaskPriority.MEDIUM;
    TaskStatus validStatus = TaskStatus.TODO;

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
        CreatedTaskData original = createdTaskData;

        taskSteps.editTask(
                UpdateTaskRequest.builder()
                        .id(original.getTaskId())
                        .title(original.getTitle() + "_updated")
                        .description("Updated description")
                        .priority(validPriority)
                        .assignee(validAssignee)
                        .sprintId(original.getSprintId())
                        .projectId(validProjectId)
                        .taskType(validTaskType)
                        .estimation(original.getEstimation())
                        .code(validCode)
                        .status(validStatus)
                        .build()
        ).checkStatusCode(200);
    }

    @Test
    public void testUpdateWithMinLengthTitle() {
        CreatedTaskData original = createdTaskData;

        taskSteps.editTask(
                UpdateTaskRequest.builder()
                        .id(original.getTaskId())
                        .title("a")
                        .description("Updated description")
                        .priority(validPriority)
                        .assignee(validAssignee)
                        .sprintId(original.getSprintId())
                        .projectId(validProjectId)
                        .taskType(validTaskType)
                        .estimation(original.getEstimation())
                        .code(validCode)
                        .status(validStatus)
                        .build()
        ).checkStatusCode(200);
    }

    @Test
    public void testUpdateWithoutDutyFields() {
        CreatedTaskData original = createdTaskData;
        // TODO: ПРОВЕРИТЬ КАКИЕ ПОЛЯ ЯВЛЯЮТСЯ ОБЯЗАТЕЛЬНЫМИ В АКТУАЛЬНОЙ АНАЛИТИКЕ

        taskSteps.editTask(
                UpdateTaskRequest.builder()
                        .id(null)
                        .title(original.getTitle() + "_updated")
                        .description("Updated description")
                        .priority(null)
                        .assignee(null)
                        .sprintId(original.getSprintId())
                        .projectId(validProjectId)
                        .taskType(null)
                        .estimation(original.getEstimation())
                        .code(validCode)
                        .status(validStatus)
                        .build()
        ).checkStatusCode(200);
    }

    @Test
    public void testUpdateTaskWithoutAuth() {

    }
}