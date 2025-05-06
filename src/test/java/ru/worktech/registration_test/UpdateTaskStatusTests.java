package ru.worktech.registration_test;


import org.testng.annotations.Test;
import ru.worktech.models.UpdateTaskStatusRequest.UpdateTaskStatusRequestBuilder;
import ru.worktech.steps.TaskSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static ru.worktech.models.UpdateTaskStatusRequest.builder;


public class UpdateTaskStatusTests {

    private final TaskSteps taskSteps = new TaskSteps();

    @Test
    public void successfullyUpdatedTaskStatusWithValidData() {
        taskSteps.updateTaskStatusStep(getUpdateTaskStatus().status("TODO").build())
                .checkStatusCode(SC_OK);
    }

    private UpdateTaskStatusRequestBuilder getUpdateTaskStatus() {
        return builder()
                .status("DONE")
                .code("TPO-0016");
    }
}