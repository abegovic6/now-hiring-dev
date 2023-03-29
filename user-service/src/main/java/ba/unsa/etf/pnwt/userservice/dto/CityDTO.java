package ba.unsa.etf.pnwt.userservice.dto;

/**
 * City DTO class
 */
public class CityDTO {
    private int id;
    private String name;

    private CountryDTO country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }
}
