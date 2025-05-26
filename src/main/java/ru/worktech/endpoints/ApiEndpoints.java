package ru.worktech.endpoints;

import lombok.Getter;

@Getter
public enum ApiEndpoints {

    REGISTRATION_ENDPOINT("/work-task/v1/registry"),
    AUTHORIZATION_ENDPOINT("/work-task/v1/login"),
    CREATE_TASK_ENDPOINT("/work-task/v1/task/create-task"),
    EDITE_TASK_ENDPOINT("/work-task/v1/task/update-task"),
    GET_TASK_BY_ID_ENDPOINT("/work-task/v1/task/{{taskId}}");

    private final String template;

    ApiEndpoints(String template) {
        this.template = template;
    }

    public String getAddress() {
        return template;
    }
}