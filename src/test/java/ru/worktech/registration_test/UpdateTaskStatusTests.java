package ru.worktech.registration_test;


import DataBaseManageServices.query.DeleteTaskFromDataBase;
import DataBaseManageServices.query.GetTaskCodeById;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.worktech.models.UpdateTaskStatusRequest.UpdateTaskStatusRequestBuilder;
import ru.worktech.models.request.CreateTaskRequest;
import ru.worktech.models.response.GetTaskByTaskCodeResponse;
import ru.worktech.steps.TaskSteps;

import static java.lang.System.currentTimeMillis;
import static org.apache.http.HttpStatus.*;
import static ru.worktech.models.UpdateTaskStatusRequest.builder;
import static ru.worktech.steps.TaskSteps.getDefaultCreateTask;


public class UpdateTaskStatusTests {

    private final TaskSteps taskSteps = new TaskSteps();
    private final DeleteTaskFromDataBase deleterTask = new DeleteTaskFromDataBase();
    private GetTaskByTaskCodeResponse createdTaskResponse;
    private final GetTaskCodeById getTaskCodeById = new GetTaskCodeById();

    @BeforeMethod
    public void setup() {
        CreateTaskRequest taskRequest = getDefaultCreateTask()
                .title("Test" + currentTimeMillis())
                .priority("MEDIUM")
                .build();
        createdTaskResponse = taskSteps.createTask(taskRequest).extractAllTaskData();
    }

    @AfterMethod
    public void teardown() {
        if (createdTaskResponse != null && createdTaskResponse.getTaskId() != null) {
            deleterTask.deleteTaskByTaskId(createdTaskResponse.getTaskId());
        }
    }

    @Test(testName = "TK-34-1-Обновление статуса задачи с валидными данными.")
    public void testUpdatingTaskStatusSuccessWithValidData() {
        taskSteps.updateTaskStatus(
                        getUpdateTaskStatus()
                                .code(getTaskCodeById.getTaskCode(createdTaskResponse.getTaskId()))
                                .status("TODO")
                                .build())
                .assertStatus(SC_OK);
    }

    @Test(testName = "TK-34-2-Обновление статуса задачи с пустым status")
    public void testUpdatingTaskStatusFailWithEmptyStatus() {
        taskSteps.updateTaskStatus(
                        getUpdateTaskStatus()
                                .code(getTaskCodeById.getTaskCode(createdTaskResponse.getTaskId()))
                                .status("")
                                .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-34-3-Обновление статуса с пустым code")
    public void testUpdatingTaskStatusSuccessWithEmptyCode() {
        taskSteps.updateTaskStatus(
                        getUpdateTaskStatus()
                                .code("")
                                .status("POSTPONED")
                                .build())
                .assertStatus(SC_NOT_FOUND);
    }

    @Test(testName = "TK-34-4-Обновление статуса задачи с несуществующим code")
    public void testUpdatingTaskStatusSuccessWithNonExistentCode() {
        taskSteps.updateTaskStatus(
                        getUpdateTaskStatus()
                                .code("AAA-0000")
                                .status("TODO")
                                .build())
                .assertStatus(SC_NOT_FOUND);
    }

    @Test(testName = "TK-34-5-Обновление статуса задачи без авторизации.")
    public void testUpdatingTaskStatusSuccessWithoutAuthorization() {
        taskSteps.updateTaskStatus(
                        getUpdateTaskStatus()
                                .code(getTaskCodeById.getTaskCode(createdTaskResponse.getTaskId()))
                                .status("TODO")
                                .build())
                .assertStatus(SC_UNAUTHORIZED);

    }

    @Test(testName = "TK-34-6-Изменение status на несуществующий.")
    public void testChangingStatusFailToNonExistent() {
        taskSteps.updateTaskStatusWithOutAuth(
                        getUpdateTaskStatus()
                                .code(getTaskCodeById.getTaskCode(createdTaskResponse.getTaskId()))
                                .status("INVALID")
                                .build())
                .assertStatus(SC_BAD_REQUEST);
    }


    private UpdateTaskStatusRequestBuilder getUpdateTaskStatus() {
        return builder()
                .status("TODO")
                .code(getTaskCodeById.getTaskCode(createdTaskResponse.getTaskId()));
    }
}