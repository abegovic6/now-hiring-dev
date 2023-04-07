package ba.unsa.etf.pnwt.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;


import java.io.Serializable;

public class SkillDTO implements Serializable {

    @NotNull(message = "Id must be specified")
    private int id;

    @NotNull(message = "User must be specified.")
    private UserDTO user;

    @NotNull(message = "Title must be specified")
    @Size(min=2, max=30, message = "Title size must be larger than 2 and smaller than 30.")
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
