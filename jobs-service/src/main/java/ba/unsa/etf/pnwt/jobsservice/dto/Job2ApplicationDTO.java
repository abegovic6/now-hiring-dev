package ba.unsa.etf.pnwt.jobsservice.dto;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Example DTO class
 */
public class Job2ApplicationDTO implements Serializable {
    private int id;
    private int jobId;
    private int applicationId;

    @Nullable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Nullable
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
}
