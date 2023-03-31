package ba.unsa.etf.pnwt.jobsservice.entity;

import ba.unsa.etf.pnwt.jobsservice.constants.DatabaseConstants;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Example entity
 */
@Entity
@Table(schema = DatabaseConstants.JOBS_SERVICE_SCHEMA, name = "Job" )
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "location")
    private String location;

    @Column(name = "valid")
    private LocalDate validTo;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    public JobEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
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
}
