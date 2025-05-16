package ru.worktech.registration_test;

import org.testng.annotations.Test;
import ru.worktech.core.BaseApiService;
import ru.worktech.steps.TaskSteps;

import static org.apache.http.HttpStatus.*;
import static ru.worktech.models.request.CreateTaskRequest.CreateTaskRequestBuilder;
import static ru.worktech.models.request.CreateTaskRequest.builder;

public class CreateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();

    @Test(testName = "TK-32-1-Создание новой задачи")
    public void testCreateTaskSuccess() {
        taskSteps.createTask(getDefaultCreateTask()
                        .build())
                .checkStatusCode(SC_CREATED);
    }

    @Test(testName = "TK-32-2-Создание задачи с минимальной длиной TITLE (1 символ)")
    public void testCreateTaskSuccessWithMinTitleLength() {
        taskSteps.createTask(getDefaultCreateTask()
                        .title("T")
                        .build())
                .checkStatusCode(SC_CREATED);
    }

    @Test(testName = "TK-32-3-Создание задачи с максимальной длиной TITLE (255 символов)")
    public void testCreateTaskSuccessWithMaxTitleLength() {
        taskSteps.createTask(getDefaultCreateTask()
                        .title("T".repeat(255))
                        .build())
                .checkStatusCode(SC_CREATED);
    }

    @Test(testName = "TK-32-4-Cоздание задачи с максимальной длиной DESCRIPTION (4096 символов)")
    public void testCreateTaskSuccessWithMaxDescriptionLength() {
        taskSteps.createTask(getDefaultCreateTask()
                        .description("C".repeat(4096))
                        .build())
                .checkStatusCode(SC_CREATED);
    }

    @Test(testName = "TK-32-5-Cоздание задачи без необязательных полей (DESCRIPTION, SPRINT_ID, ESTIMATION)")
    public void testCreateTaskSuccessWithoutOptionalFields() {
        taskSteps.createTask(getDefaultCreateTask()
                        .description(null)
                        .sprintId(null)
                        .estimation(null)
                        .build())
                .checkStatusCode(SC_CREATED);
    }

    @Test(testName = "TK-32-6-Создание задачи без обязательного поля (TITLE)")
    public void testCreateTaskFailWithoutTitle() {
        taskSteps.createTask(getDefaultCreateTask()
                        .title(null)
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-7-Создание задачи без обязательного поля (PRIORITY)")
    public void testCreateTaskFailWithoutPriority() {
        taskSteps.createTask(getDefaultCreateTask()
                        .priority(null)
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-8-Создание задачи с некоректным значением (PRIORITY)")
    public void testCreateTaskFailWithInvalidPriority() {
        taskSteps.createTask(getDefaultCreateTask()
                        .priority("INVALID")
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-9-Создание задачи без обязательного поля (ASSIGNEE)")
    public void testCreateTaskFailWithoutAssignee() {
        taskSteps.createTask(getDefaultCreateTask()
                        .assignee(null)
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-10-Создание задачи с некоректным значением (ASSIGNEE)")
    public void testCreateTaskFailWithInvalidAssignee() {
        taskSteps.createTask(getDefaultCreateTask()
                        .assignee("INVALID")
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-11-Создание задачи без обязательного поля (PROJECT_ID)")
    public void testCreateTaskFailWithoutProjectId() {
        taskSteps.createTask(getDefaultCreateTask()
                        .projectId(null)
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-12-Создание задачи с некоректным значением (PROJECT_ID)")
    public void testCreateTaskFailWithInvalidProjectId() {
        taskSteps.createTask(getDefaultCreateTask()
                        .projectId("INVALID")
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-13-Создание задачи без обязательного поля (TASK_TYPE)")
    public void testCreateTaskFailWithoutTaskType() {
        taskSteps.createTask(getDefaultCreateTask()
                        .taskType(null)
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-14-Создание задачи с некоректным значением (TASK_TYPE)")
    public void testCreateTaskFailWithInvalidTaskType() {
        taskSteps.createTask(getDefaultCreateTask()
                        .taskType("INVALID")
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-15-Создание задачи с отрицательным значением (ESTIMATION)")
    public void testCreateTaskFailWithNegativeEstimation() {
        taskSteps.createTask(getDefaultCreateTask()
                        .estimation(-1)
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-16-Создание задачи с некоректным значением поля (SPRINT_ID)")
    public void testCreateTaskFailWithInvalidSprintId() {
        taskSteps.createTask(getDefaultCreateTask()
                        .sprintId("INVALID")
                        .build())
                .checkStatusCode(SC_BAD_REQUEST);
    }

    @Test(testName = "TK-32-17-Создание задачи неавторизованным пользователем")
    public void testCreateTaskFailUnauthenticated() {
        BaseApiService.setIgnoreAuth(true);
        taskSteps.createTask(getDefaultCreateTask()
                        .build())
                .checkStatusCode(SC_UNAUTHORIZED);
        BaseApiService.setIgnoreAuth(false);
    }

    @Test(testName = "TK-32-18-Создание задачи с повторяющимися ключами json")
    public void createTaskWithDuplicateJsonKeys() {
    }

    private CreateTaskRequestBuilder getDefaultCreateTask() {
        return builder()
                .title("TestEntity")
                .description("Correct")
                .assignee("830c1f1a-1a10-4a77-b8c0-81d25747bb2f")
                .priority("HIGH")
                .projectId("project-id-929")
                .sprintId("6c17g1c0-5j7f-49vy-ay1a-m98766c6t91")
                .taskType("BUG")
                .estimation(5);
    }
}
