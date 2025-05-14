package ru.worktech.registration_test;


import org.testng.annotations.Test;
import ru.worktech.models.UpdateTaskStatusRequest.UpdateTaskStatusRequestBuilder;
import ru.worktech.steps.TaskSteps;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static ru.worktech.models.UpdateTaskStatusRequest.builder;


public class UpdateTaskStatusTests {

    private final TaskSteps taskSteps = new TaskSteps();

    @Test(testName = "TK-34-1-Обновление статуса задачи с валидными данными.")
    public void testUpdatingTaskStatusSuccessWithValidData() {
//        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().status("TODO").build())
//                .checkStatusCode(SC_OK);
    }

    @Test(testName = "TK-34-2-Обновление статуса задачи с пустым status")
    public void testUpdatingTaskStatusFailWithEmptyStatus() {

    }

    @Test(testName = "TK-34-3-Обновление статуса с пустым code")
    public void testUpdatingTaskStatusSuccessWithEmptyCode() {

    }

    @Test(testName = "TK-34-4-Обновление статуса задачи с несуществующим code")
    public void testUpdatingTaskStatusSuccessWithNonExistentCode() {
//        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().status("AAA-0000").build())
//                .checkStatusCode(SC_NOT_FOUND);
    }

    @Test(testName = "TK-34-5-Обновление статуса задачи без авторизации.")
    public void testUpdatingTaskStatusSuccessWithoutAuthorization() {

    }

    @Test(testName = "TK-34-6-Изменение status на несуществующий.")
    public void testChangingStatusFailToNonExistent() {

    }


    private UpdateTaskStatusRequestBuilder getUpdateTaskStatus() {
        return builder()
                .status("TODO")
                .code("TPO-0016");
    }
}