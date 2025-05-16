package ru.worktech.registration_test;


import org.testng.annotations.Test;
import ru.worktech.models.UpdateTaskStatusRequest.UpdateTaskStatusRequestBuilder;
import ru.worktech.steps.TaskSteps;

import static org.apache.http.HttpStatus.*;
import static ru.worktech.models.UpdateTaskStatusRequest.builder;


public class UpdateTaskStatusTests {

    private final TaskSteps taskSteps = new TaskSteps();

    @Test(testName = "TK-34-1-Обновление статуса задачи с валидными данными.")
    public void testUpdatingTaskStatusSuccessWithValidData() {
        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().code("TPO-0016").status("TODO").build())
                .checkStatusCode(SC_OK);
    }

    @Test(testName = "TK-34-2-Обновление статуса задачи с пустым status")
    public void testUpdatingTaskStatusFailWithEmptyStatus() {
        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().code("TPO-0016").status("").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-34-3-Обновление статуса с пустым code")
    public void testUpdatingTaskStatusSuccessWithEmptyCode() {
        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().code("").status("POSTPONED").build())
                .checkStatusCode(SC_NOT_FOUND);
    }

    @Test(testName = "TK-34-4-Обновление статуса задачи с несуществующим code")
    public void testUpdatingTaskStatusSuccessWithNonExistentCode() {
        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().code("AAA-0000").status("TODO").build())
                .checkStatusCode(SC_NOT_FOUND);
    }

    @Test(testName = "TK-34-5-Обновление статуса задачи без авторизации.")
    public void testUpdatingTaskStatusSuccessWithoutAuthorization() {
        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().code("TPO-0016").status("TODO").build())
                .checkStatusCode(SC_UNAUTHORIZED);
    }

    @Test(testName = "TK-34-6-Изменение status на несуществующий.")
    public void testChangingStatusFailToNonExistent() {
        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().code("TPO-0016").status("INVALID").build())
                .checkStatusCode(SC_BAD_REQUEST);
    }


    private UpdateTaskStatusRequestBuilder getUpdateTaskStatus() {
        return builder()
                .status("TODO")
                .code("TPO-0016");
    }
}