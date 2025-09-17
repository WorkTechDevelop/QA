package ru.worktech.registration_test;

import database.query.TaskQuery;
import database.query.GetTaskCodeById;
import enums.TaskPriority;
import enums.TaskStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.worktech.steps.TaskSteps;

import static java.lang.System.currentTimeMillis;
import static java.util.Objects.nonNull;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static testDataGenerator.TestDataGenerator.getDefaultCreateTask;
import static testDataGenerator.TestDataGenerator.getDefaultUpdateTaskStatus;

public class UpdateTaskStatusTests {

    private final TaskSteps taskSteps = new TaskSteps();
    private final TaskQuery deleterTask = new TaskQuery();
    private String createdTaskId;
    private final GetTaskCodeById getTaskCodeById = new GetTaskCodeById();

    @BeforeClass
    public void setup() {
        var taskRequest = getDefaultCreateTask()
                .setTitle("Test" + currentTimeMillis())
                .setPriority(TaskPriority.LOW);
        createdTaskId = taskSteps.createTask(taskRequest).getStringByJsonPath("id");
    }

    @AfterClass
    public void tearDown() {
        if (nonNull(createdTaskId)) {
            deleterTask.deleteTaskByTaskId(createdTaskId);
        }
    }

    @Test(testName = "TK-34-1-Обновление статуса задачи с валидными данными.")
    public void testUpdatingTaskStatusSuccessWithValidData() {
        var request = getDefaultUpdateTaskStatus()
                .setCode(getTaskCodeById.getTaskCode(createdTaskId));

        taskSteps.updateTaskStatus(request)
                .assertStatus(SC_OK);
    }

    @Test(testName = "TK-34-5-Обновление статуса задачи без авторизации.")
    public void testUpdatingTaskStatusSuccessWithoutAuthorization() {
        var request = getDefaultUpdateTaskStatus()
                .setCode(getTaskCodeById.getTaskCode(createdTaskId))
                .setStatus(1);

        taskSteps.updateTaskStatusWithOutAuth(request)
                .assertStatus(SC_BAD_REQUEST);
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                {getTaskCodeById.getTaskCode(createdTaskId), null},
                {null, 1},
                {"AAA-0000", 1},
        };
    }

    @Test(testName = "ТК-34- Обновление статуса задачи с невалидными данными", dataProvider = "dataProvider")
    public void testUpdateTaskStatusFail(String code, TaskStatus status) {
        var request = getDefaultUpdateTaskStatus()
                .setCode(code)
                .setStatus(1);

        taskSteps.updateTaskStatus(request)
                .assertStatus(SC_BAD_REQUEST);
    }
}