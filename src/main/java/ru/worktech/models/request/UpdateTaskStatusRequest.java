package ru.worktech.models.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors (chain = true)
public class UpdateTaskStatusRequest {

    public String code;
    public int status;
}
