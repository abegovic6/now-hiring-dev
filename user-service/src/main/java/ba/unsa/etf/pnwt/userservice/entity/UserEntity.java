package ba.unsa.etf.pnwt.userservice.entity;

import ba.unsa.etf.pnwt.userservice.constants.DatabaseConstants;
import ba.unsa.etf.pnwt.userservice.constants.UserType;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(schema = DatabaseConstants.USER_SERVICE_SCHEMA, name = "user" )
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;

    @Column(name = "eMail", unique = true, nullable = false)
    private String email;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "description")
    private String description;

    @Column(name = "creationTS", nullable = false)
    private ZonedDateTime creationTS;

    @Column(name = "modificationTS")
    private ZonedDateTime modificationTS;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "userType", nullable = false)
    private UserType userType;

    @Column(name = "password", nullable = false)
    private String password;

    public UserEntity() {
    }

    public UserEntity(String uuid, String firstName, CityEntity cityEntity) {
        this.uuid = uuid;
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String cdid) {
        this.uuid = cdid;
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

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public ZonedDateTime getCreationTS() {
        return creationTS;
    }

    public void setCreationTS(ZonedDateTime creationTS) {
        this.creationTS = creationTS;
    }

    public ZonedDateTime getModificationTS() {
        return modificationTS;
    }

    public void setModificationTS(ZonedDateTime modificationTS) {
        this.modificationTS = modificationTS;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
