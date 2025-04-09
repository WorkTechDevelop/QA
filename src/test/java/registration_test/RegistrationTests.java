package registration_test;

import org.apache.http.HttpStatus;
import org.example.models.RegistrationRequest;
import org.example.steps.UserSteps;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class RegistrationTests {

    private final UserSteps userSteps = new UserSteps();

    @Test
    public void testSuccessfulRegistration() {
        RegistrationRequest user = new RegistrationRequest(
                "testttiks@gmail.com",
                "password123",
                "password123",
                "Doe",
                "John",
                "Middle",
                "1234567890",
                "27-05-1995",
                "MALE"
        );
        userSteps.registerUser(user)
                .checkStatusCode(SC_OK);


    }
}
