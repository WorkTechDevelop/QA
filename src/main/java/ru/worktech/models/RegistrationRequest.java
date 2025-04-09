package ru.worktech.models;

import lombok.Data;

@Data
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

    public RegistrationRequest(String email, String password, String confirmPassword, String lastName, String firstName,
                               String middleName, String phone, String birthDate, String gender) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
