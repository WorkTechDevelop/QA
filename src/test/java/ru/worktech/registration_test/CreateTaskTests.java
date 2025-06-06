package ru.worktech.registration_test;

import DataBaseManageServices.query.DeleteTaskFromDataBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.worktech.steps.TaskSteps;

import java.util.Objects;

import static org.apache.http.HttpStatus.*;
import static ru.worktech.models.request.CreateTaskRequest.CreateTaskRequestBuilder;
import static ru.worktech.models.request.CreateTaskRequest.builder;

public class CreateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();
    private DeleteTaskFromDataBase deleteTaskFromDataBase = new DeleteTaskFromDataBase();
    private String taskId;

    @AfterMethod
    public void afterMethod() {
        if (Objects.nonNull(taskId)) {
            deleteTaskFromDataBase.deleteTaskByTaskId(taskId);
        }
    }

    @Test(testName = "TK-32-1-Создание новой задачи")
    public void testCreateTaskSuccess() {
        taskId = taskSteps.createTask(getDefaultCreateTask()
                        .build()).assertStatus(SC_CREATED)
                .extractAllTaskData()
                .getTaskId();

    }

    @Test(testName = "TK-32-2-Создание задачи с минимальной длиной TITLE (1 символ)")
    public void testCreateTaskSuccessWithMinTitleLength() {
        taskId = taskSteps.createTask(getDefaultCreateTask()
                        .title("T")
                        .build())
                .assertStatus(SC_CREATED)
                .extractAllTaskData()
                .getTaskId();

    }

    @Test(testName = "TK-32-3-Создание задачи с максимальной длиной TITLE (255 символов)")
    public void testCreateTaskSuccessWithMaxTitleLength() {
        taskId = taskSteps.createTask(getDefaultCreateTask()
                        .title("T".repeat(255))
                        .build()).assertStatus(SC_CREATED)
                .extractAllTaskData()
                .getTaskId();
    }

    @Test(testName = "TK-32-4-Cоздание задачи с максимальной длиной DESCRIPTION (4096 символов)")
    public void testCreateTaskSuccessWithMaxDescriptionLength() {
        taskId = taskSteps.createTask(getDefaultCreateTask()
                        .description("C".repeat(4096))
                        .build())
                .assertStatus(SC_CREATED)
                .extractAllTaskData()
                .getTaskId();
    }

    @Test(testName = "TK-32-5-Cоздание задачи без необязательных полей (DESCRIPTION, SPRINT_ID, ESTIMATION)")
    public void testCreateTaskSuccessWithoutOptionalFields() {
        taskId = taskSteps.createTask(getDefaultCreateTask()
                        .description(null)
                        .sprintId(null)
                        .estimation(null)
                        .build())
                .assertStatus(SC_CREATED)
                .extractAllTaskData()
                .getTaskId();
    }

    @Test(testName = "TK-32-6-Создание задачи без обязательного поля (TITLE)")
    public void testCreateTaskFailWithoutTitle() {
        taskSteps.createTask(getDefaultCreateTask()
                        .title(null)
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-7-Создание задачи без обязательного поля (PRIORITY)")
    public void testCreateTaskFailWithoutPriority() {
        taskSteps.createTask(getDefaultCreateTask()
                        .priority(null)
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-8-Создание задачи с некоректным значением (PRIORITY)")
    public void testCreateTaskFailWithInvalidPriority() {
        taskSteps.createTask(getDefaultCreateTask()
                        .priority("INVALID")
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-9-Создание задачи без обязательного поля (ASSIGNEE)")
    public void testCreateTaskFailWithoutAssignee() {
        taskSteps.createTask(getDefaultCreateTask()
                        .assignee(null)
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-10-Создание задачи с некоректным значением (ASSIGNEE)")
    public void testCreateTaskFailWithInvalidAssignee() {
        taskSteps.createTask(getDefaultCreateTask()
                        .assignee("INVALID")
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-11-Создание задачи без обязательного поля (PROJECT_ID)")
    public void testCreateTaskFailWithoutProjectId() {
        taskSteps.createTask(getDefaultCreateTask()
                        .projectId(null)
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-12-Создание задачи с некоректным значением (PROJECT_ID)")
    public void testCreateTaskFailWithInvalidProjectId() {
        taskSteps.createTask(getDefaultCreateTask()
                        .projectId("INVALID")
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-13-Создание задачи без обязательного поля (TASK_TYPE)")
    public void testCreateTaskFailWithoutTaskType() {
        taskSteps.createTask(getDefaultCreateTask()
                        .taskType(null)
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-14-Создание задачи с некоректным значением (TASK_TYPE)")
    public void testCreateTaskFailWithInvalidTaskType() {
        taskSteps.createTask(getDefaultCreateTask()
                        .taskType("INVALID")
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-15-Создание задачи с отрицательным значением (ESTIMATION)")
    public void testCreateTaskFailWithNegativeEstimation() {
        taskSteps.createTask(getDefaultCreateTask()
                        .estimation(-1)
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-16-Создание задачи с некоректным значением поля (SPRINT_ID)")
    public void testCreateTaskFailWithInvalidSprintId() {
        taskSteps.createTask(getDefaultCreateTask()
                        .sprintId("INVALID")
                        .build())
                .assertStatus(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-17-Создание задачи неавторизованным пользователем")
    public void testCreateTaskFailUnauthenticated() {
        taskSteps.createTaskWithOutAuth(getDefaultCreateTask()
                        .build())
                .assertStatus(SC_UNAUTHORIZED);
    }

    private CreateTaskRequestBuilder getDefaultCreateTask() {
        return builder()
                .title("TestEntity")
                .description("Correct")
                .assignee("534357db-f8a4-49d4-9951-2f863ff53547")
                .priority("HIGH")
                .projectId("project-123")
                .sprintId("")
                .taskType("BUG")
                .estimation(5);
    }
}
