package ru.worktech.models.request;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors (chain = true)
public class RegistrationRequest {

    private String email;
    private String password;
    private String confirmPassword;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String birthDate;
    private String gender;

}
