package ba.unsa.etf.pnwt.jobsservice.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Example DTO class
 */
public class UserDTO implements Serializable {
    @NotNull(message = "ID must be specified")
    private String uid;
    @NotNull(message = "User Type must be specified")
    private String userType;
    private String companyName;
    private String firstName;
    private String lastName;
    @NotNull(message = "E-mail must be specified")
    private String email;
    private String description;
    private String location;

    private Integer id;

    @Nullable
    public String getUid() {
        return uid;
    }

    public void setUid(String id) {
        this.uid = id;
    }

    @Nullable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Nullable
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
