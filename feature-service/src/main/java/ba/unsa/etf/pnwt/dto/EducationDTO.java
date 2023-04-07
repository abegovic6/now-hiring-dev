package ba.unsa.etf.pnwt.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

public class EducationDTO implements Serializable {

    @NotNull(message = "Id has to be specified")
    private int id;

    @NotNull(message = "User must be specified")
    private UserDTO user;

    @NotNull(message = "Title has to be specified.")
    @Size(min=2, max=30, message = "Title size must be larger than 2 and smaller than 30.")
    private String title;

    @Size(max=1000, message = "Description can not have more than 1000 characters.")
    private String description;

    @Min(value = 1, message = "Months can not have value smaller than 1.")
    @Max(value = 12, message = "Months can not have value larger than 12.")
    private Integer startingMonth;

    private Integer startingYear;

    @Min(value = 1, message = "Months can not have value smaller than 1.")
    @Max(value = 12, message = "Months can not have value larger than 12.")
    private Integer endMonth;

    private Integer endYear;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartingMonth() {
        return startingMonth;
    }

    public void setStartingMonth(Integer startingMonth) {
        this.startingMonth = startingMonth;
    }

    public Integer getStartingYear() {
        return startingYear;
    }

    public void setStartingYear(Integer startingYear) {
        this.startingYear = startingYear;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
