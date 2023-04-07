package ba.unsa.etf.pnwt.jobsservice.dto;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Example DTO class
 */
public class ApplicationDTO implements Serializable {
    private int id;
    @NotNull(message = "User ID must be specified")
    private String userId;
    @NotNull(message = "Job ID must be specified")
    private Integer jobId;
    private String coverLetter;
    private String firstName;
    private String lastName;
    private String location;
    private String description;

    @Nullable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
