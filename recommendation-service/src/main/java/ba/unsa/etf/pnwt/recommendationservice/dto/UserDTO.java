package ba.unsa.etf.pnwt.recommendationservice.dto;

import org.springframework.lang.Nullable;

import java.io.Serializable;

public class UserDTO{
    private long id;
    private String uuid;
    private String name;
    private String email;

    public UserDTO() {
    }

    public UserDTO(long id, String uuid, String name, String email) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
