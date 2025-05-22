package ru.worktech.tests;

import DataBaseManageServices.query.DeleteTaskFromDataBase;
import DataBaseManageServices.query.GetTaskCodeById;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.worktech.models.request.CreateTaskRequest;
import ru.worktech.models.response.GetTaskByTaskCodeResponse;
import ru.worktech.steps.TaskSteps;

import static enums.TaskPriority.MEDIUM;
import static enums.TaskStatus.*;
import static enums.TaskType.BUG;
import static enums.TaskType.TASK;
import static java.lang.System.currentTimeMillis;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.worktech.models.request.UpdateTaskRequest.builder;
import static ru.worktech.steps.TaskSteps.getDefaultCreateTask;

public class UpdateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();
    private final DeleteTaskFromDataBase deleterTask = new DeleteTaskFromDataBase();
    private CreateTaskRequest taskRequest;
    private GetTaskByTaskCodeResponse createdTaskResponse;
    private final GetTaskCodeById getTaskCodeById = new GetTaskCodeById();
    private static String taskCode;

    @BeforeMethod
    public void setup() {
        taskRequest = getDefaultCreateTask()
                .title("Test" + currentTimeMillis())
                .priority("MEDIUM")
                .build();
        createdTaskResponse = taskSteps.createTask(taskRequest).extractAllTaskData();
        taskCode = getTaskCodeById.getTaskCode(createdTaskResponse.getTaskId());
    }

    @AfterMethod
    public void teardown() {
        if (createdTaskResponse != null && createdTaskResponse.getTaskId() != null) {
            deleterTask.deleteTaskByTaskId(createdTaskResponse.getTaskId());
        }
    }

    @Test
    public void testSuccessfulUpdateTask() {
        taskSteps.updateTask(
                builder()
                        .id(createdTaskResponse.getTaskId())
                        .title(taskRequest.getTitle() + "_updated")
                        .description("Updated description")
                        .priority(MEDIUM)
                        .assignee(taskRequest.getAssignee())
                        .sprintId(taskRequest.getSprintId())
                        .projectId(taskRequest.getProjectId())
                        .taskType(TASK)
                        .estimation(taskRequest.getEstimation())
                        .code(taskCode)
                        .status(REVIEW)
                        .build()
        ).checkStatusCode(SC_OK);
    }

    @Test
    public void testUpdateWithMinLengthTitle() {
        taskSteps.updateTask(
                builder()
                        .id(createdTaskResponse.getTaskId())
                        .title("a")
                        .description("Updated description")
                        .priority(MEDIUM)
                        .assignee(taskRequest.getAssignee())
                        .sprintId(taskRequest.getSprintId())
                        .projectId(taskRequest.getProjectId())
                        .taskType(BUG)
                        .estimation((taskRequest.getEstimation()))
                        .code(getTaskCodeById.getTaskCode(createdTaskResponse.getTaskId()))
                        .status(DONE)
                        .build()
        ).checkStatusCode(SC_OK);
    }

    @Test
    public void testUpdateWithoutDutyFields() {
        // TODO: ПРОВЕРИТЬ КАКИЕ ПОЛЯ ЯВЛЯЮТСЯ ОБЯЗАТЕЛЬНЫМИ В АКТУАЛЬНОЙ АНАЛИТИКЕ

        taskSteps.updateTask(
                builder()
                        .id(null)
                        .title(taskRequest.getTitle() + "_updated")
                        .description("Updated description")
                        .priority(null)
                        .assignee(null)
                        .sprintId(taskRequest.getSprintId())
                        .projectId(taskRequest.getProjectId())
                        .taskType(null)
                        .estimation((taskRequest.getEstimation()))
                        .code(createdTaskResponse.getCode())
                        .status(IN_PROGRESS)
                        .build()
        ).checkStatusCode(SC_OK);
    }
}