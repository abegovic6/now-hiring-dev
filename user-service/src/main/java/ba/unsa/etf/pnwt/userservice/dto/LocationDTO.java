package ba.unsa.etf.pnwt.userservice.dto;

/**
 * City DTO class
 */
public class LocationDTO {
    private int id;
    private String city;
    private String country;
    private String displayValue;

    public LocationDTO() {
    }

    public LocationDTO(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}
