package ba.unsa.etf.pnwt.jobsservice.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Example DTO class
 */
public class JobDTO implements Serializable {
    private int id;
    @NotNull(message = "Job title must be specified")
    private String title;
    @NotNull(message = "Job location must be specified")
    private String location;

    @NotNull(message = "Company ID must be specified")
    private String companyId;

    private String companyName;
    private String validTo;
    private String type;
    private String description;


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


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo.toString();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
