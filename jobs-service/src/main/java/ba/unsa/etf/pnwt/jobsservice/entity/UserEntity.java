package ba.unsa.etf.pnwt.jobsservice.entity;

import ba.unsa.etf.pnwt.jobsservice.constants.DatabaseConstants;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

/**
 * Example entity
 */
@Entity
@Table(schema = DatabaseConstants.JOBS_SERVICE_SCHEMA, name = "User" )
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uid", unique = true, nullable = false)
    private String uid;

    @Column(name = "eMail", unique = true, nullable = false)
    private String email;

    @Column(name = "userType", nullable = false)
    private String userType;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    public UserEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String udid) {
        this.uid = udid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
