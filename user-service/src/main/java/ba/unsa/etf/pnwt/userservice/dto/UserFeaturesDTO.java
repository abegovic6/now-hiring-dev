package ba.unsa.etf.pnwt.userservice.dto;

public class UserFeaturesDTO {
    private int id;
    private String uuid;

    private String email;

    public UserFeaturesDTO() {
    }

    public UserFeaturesDTO(int id, String uuid, String email) {
        this.id = id;
        this.uuid = uuid;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
