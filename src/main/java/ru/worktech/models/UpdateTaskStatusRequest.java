package ru.worktech.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTaskStatusRequest {

    public String code;
    public String status;

    public UpdateTaskStatusRequest(String code, String status) {
        this.code = code;
        this.status = status;
    }
}
