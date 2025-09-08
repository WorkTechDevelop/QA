package ru.worktech.registration_test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.worktech.common.BaseApiTests;
import ru.worktech.common.SharedUserTest;

import static java.util.Objects.nonNull;
import static org.apache.http.HttpStatus.*;
import static testDataGenerator.TestDataGenerator.getDefaultCreateTask;
import static testDataGenerator.TestDataGenerator.getDefaultCreateTaskRequestMap;

public class CreateTaskTests extends BaseApiTests {

    private String taskId;


    @AfterMethod
    public void afterMethod() {
        if (nonNull(taskId)) {
            getTaskQueries().deleteTask(taskId);
        }
    }

    @Test(testName = "TK-32-5-Cоздание задачи без необязательных полей (DESCRIPTION, SPRINT_ID, ESTIMATION)")
    public void testCreateTaskSuccessWithoutOptionalFields() {
        var request = getDefaultCreateTask()
                .setDescription(null)
                .setSprintId(null)
                .setEstimation(null);

        taskId = getTaskSteps().createTask(request)
                .assertStatus(SC_OK)
                .getStringByJsonPath("taskId");
    }

    @Test(testName = "TK-32-17-Создание задачи неавторизованным пользователем")
    public void testCreateTaskFailUnauthenticated() {
        var request = getDefaultCreateTask();

        getTaskSteps().createTaskWithOutAuth(request)
                .assertStatus(SC_UNAUTHORIZED);
    }

    @DataProvider(name = "validFieldValues")
    public Object[][] dataProvider() {
        return new Object[][]{
                {"", ""},
                {"title", "T"},
                {"title", "t".repeat(255)},
                {"description", "D".repeat(4096)},
        };
    }

    @Test(testName = "TK-32-Создание новой задачи с допустимыми значениями полей 'title', 'description'",
            dataProvider = "validFieldValues")
    public void createTask(String field, String value) {
        var request = getDefaultCreateTaskRequestMap();
        request.put(field, value);

        getTaskSteps().createTaskMap(request)
                .assertStatus(SC_CREATED);
    }

    @DataProvider(name = "InvalidFieldValues")
    public Object[][] dataProvider1() {
        return new Object[][]{
                {"title", null},
                {"priority", null},
                {"priority", "INVALID"},
                {"assignee", null},
                {"assignee", "INVALID"},
                {"projectId", null},
                {"projectId", "INVALID"},
                {"taskType", null},
                {"taskType", "INVALID"},
                {"estimation", "-1"},
                {"sprintId", "INVALID"},
        };
    }

    @Test(testName = "ТК-32-Негативные ТК на создание задачи", dataProvider = "InvalidFieldValues")
    public void createTaskWithoutRequiredFields(String field, String value) {
        var request = getDefaultCreateTaskRequestMap();
        request.put(field, value);

        getTaskSteps().createTaskMap(request)
                .assertStatus(SC_BAD_REQUEST);
    }
}
