package database.utils;

import database.dto.UserDTO;
import database.query.UserQuery;

import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

public class UserFactory {

    private static final String DEFAULT_PASSWORD_HASH =
            "$2a$10$KaVHluqzpnf5SZt5AQMwHu012fwB2DE803njWq9y19cddH3Qj8baW";


    public static UserDTO create() throws SQLException {
        UserQuery userQuery = new UserQuery();
        String email = "user-" + System.nanoTime() + "@example.com";
        java.sql.Timestamp confirmedAt = new java.sql.Timestamp(Instant.now().toEpochMilli() - 24 * 60 * 60 * 1000);

        UserDTO user = UserDTO.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .is_active(true)
                .first_name("Test")
                .last_name("User")
                .gender("MALE")
                .password(DEFAULT_PASSWORD_HASH)
                .confirmed_at(confirmedAt)
                .build();

        userQuery.create(user);
        userQuery.createRole(user.getId(), "PROJECT_MEMBER");
        userQuery.closeConnection();

        return user;
    }

    public static void delete(String email) throws SQLException {
        UserQuery userQuery = new UserQuery();
        userQuery.deleteByEmail(email);
        userQuery.closeConnection();
    }
}
