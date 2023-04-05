package ba.unsa.etf.pnwt.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.hibernate.validator.constraints.Email;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDTO implements Serializable {

    @NotNull(message = "User ID must be specified")
    private int id;

    @NotNull(message = "User UUID must be specified")
    private String uuid;

    @NotEmpty(message = "User email must be specified")
    @Email(message = "User email must be formatted correctly.")
    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
