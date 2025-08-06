package database.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
public class UserDTO {

    private String id;
    @NotNull
    private boolean is_active;
    private Date birth_date;
    @NotNull
    private String email;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private String gender;
    private String middle_name;
    @NotNull
    private String password;
    private String phone;
    private Timestamp confirmed_at;
    private String confirmation_token;
    private String last_project_id;
}
