package ba.unsa.etf.pnwt.userservice.dto;

import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * User DTO class
 */
public class UserDTO implements Serializable {
    private String cdid;
    private String firstName;

    private String lastName;
    private CityDTO city;
    private String description;
    private List<UserDTO> connections;



    public String getCdid() {
        return cdid;
    }

    public void setCdid(String cdid) {
        this.cdid = cdid;
    }

    @Nullable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public List<UserDTO> getConnections() {
        return connections;
    }

    public void setConnections(List<UserDTO> connections) {
        this.connections = connections;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
