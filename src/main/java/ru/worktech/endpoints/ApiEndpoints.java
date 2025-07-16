package ru.worktech.endpoints;

import lombok.Getter;

@Getter
public enum ApiEndpoints {

    REGISTRATION_ENDPOINT("/work-task/api/v1/registration/registry"),
    AUTHORIZATION_ENDPOINT("/work-task/api/v1/auth/login"),
    CREATE_TASK_ENDPOINT("/work-task/api/v1/tasks/create"),
    EDITE_TASK_ENDPOINT("/work-task/api/v1/tasks/update"),
    UPDATE_TASK_STATUS("/work-task/api/v1/tasks/update-status");

    private final String template;

    ApiEndpoints(String template) {
        this.template = template;
    }

    public String getAddress() {
        return template;
    }
}