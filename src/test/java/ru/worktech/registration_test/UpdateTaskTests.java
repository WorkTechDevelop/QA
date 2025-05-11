package ru.worktech.registration_test;

import DataBaseManageServices.DeleteTaskFromDataBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.worktech.models.UpdateTaskRequest;
import ru.worktech.steps.TaskSteps;
import testDataGenerator.TaskTitleGenerator;

public class UpdateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();
    DeleteTaskFromDataBase deleterTask = new DeleteTaskFromDataBase();

    private String generatedTitle = TaskTitleGenerator.generateTaskTitle();
    private String createdTaskId;

    @BeforeMethod
    public void setup() {
        generatedTitle = TaskTitleGenerator.generateTaskTitle();
        createdTaskId = taskSteps.createTask(
                TaskSteps.getDefaultCreateTask()
                        .title(generatedTitle)
                        .build()
        ).extractTaskId();
    }

    @AfterMethod
    public void teardown() {
        if (createdTaskId != null) {
            deleterTask.deleteTaskByTaskId(createdTaskId);
        }
    }
    // TODO: СДЕЛАТЬ ENUMS ДЛЯ НЕКОТОРЫХ ПОЛЕЙ И ПРОПИСАТЬ ВАЛИДНЫЕ ПОЛЯ ДЛЯ ЗАПРОСА
    @Test
    public void testSuccessfulUpdateTask() {
        taskSteps.editTask(getDefaultUpdateTask()
                        .taskId(createdTaskId)
                        .title(generatedTitle + "_updated")
                        .description("Обновленное описание")
                        .priority("HIGH")
                        .assignee("user123")
                        .sprintId("sprint-456")
                        .estimation(5)
                        .code("")
                        .status("OPEN")
                        .build())
                .checkStatusCode(200);
    }

    private UpdateTaskRequest.UpdateTaskRequestBuilder getDefaultUpdateTask() {
        return UpdateTaskRequest.builder()
                .taskId("")
                .title("TestEntity123")
                .description("Correct")
                .priority("HIGH")
                .assignee("user123")
                .sprintId("sprint-456")
                .estimation(5)
                .status("OPEN");
    }
}