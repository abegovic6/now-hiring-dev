package ba.unsa.etf.pnwt.userservice.dto;


public class UserJobDTO {

    private String uid;

    private String userType;
    private String companyName;
    private String firstName;
    private String lastName;

    private String email;
    private String description;
    private String location;

    private Integer id;

    public UserJobDTO() {
    }

    public UserJobDTO(String uid, String userType, String companyName, String firstName, String lastName, String email, String description, String location, Integer id) {
        this.uid = uid;
        this.userType = userType;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.location = location;
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String id) {
        this.uid = id;
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
