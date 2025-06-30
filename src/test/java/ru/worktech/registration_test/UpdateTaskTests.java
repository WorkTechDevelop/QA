package ru.worktech.registration_test;

import DataBaseManageServices.query.DeleteTaskFromDataBase;
import DataBaseManageServices.query.GetTaskCodeById;
import enums.TaskPriority;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.worktech.models.TaskDto;
import ru.worktech.steps.TaskSteps;

import static java.lang.System.currentTimeMillis;
import static java.util.Objects.nonNull;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static testDataGenerator.TestDataGenerator.getDefaultCreateTask;
import static testDataGenerator.TestDataGenerator.getDefaultUpdateTask;

public class UpdateTaskTests {

    private final TaskSteps taskSteps = new TaskSteps();
    private final DeleteTaskFromDataBase deleterTask = new DeleteTaskFromDataBase();
    private TaskDto taskRequest;
    private String createdTaskId;
    private final GetTaskCodeById getTaskCodeById = new GetTaskCodeById();
    private static String taskCode;

    @BeforeClass
    public void setup() {
        taskRequest = getDefaultCreateTask()
                .setTitle("Test" + currentTimeMillis())
                .setPriority(TaskPriority.LOW)
        ;
        createdTaskId = taskSteps.createTask(taskRequest).getStringByJsonPath("taskID");
        taskCode = getTaskCodeById.getTaskCode(createdTaskId);
    }

    @AfterClass
    public void tearDown() {
        if (nonNull(createdTaskId)) {
            deleterTask.deleteTaskByTaskId(createdTaskId);
        }
    }

    @Test(testName = "TK-32-1-Успешное редактирование задачи с правильными данными")
    public void testUpdateTaskSuccess() {
        taskSteps.updateTask(getDefaultUpdateTask()
                        .setTaskId(createdTaskId))
                .assertStatus(SC_OK);
    }

    @Test(testName = "TK-32-2-Редактирование задачи с минимальной длиной TITLE (1 символ)")
    public void testUpdateTaskWithMinLengthTitle() {
        var request = getDefaultUpdateTask()
                .setTaskId(createdTaskId)
                .setTitle("T");

        taskSteps.updateTask(request)
                .assertStatus(SC_OK);
    }

    @Test(testName = "ТК-32-3-Редактирование задачи без обязательных полей")
    public void testUpdateWithoutDutyFields() {
        var request = getDefaultUpdateTask()
                .setTaskId("")
                .setTitle("")
                .setPriority(TaskPriority.NULL)
                .setAssignee("");

        taskSteps.updateTask(request)
                .assertStatus(SC_BAD_REQUEST);
    }
}