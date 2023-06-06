package ba.unsa.etf.pnwt.userservice.params;

import ba.unsa.etf.pnwt.userservice.constants.Role;

public class UserParams {
    private String searchValue;
    private String city;
    private String country;
    private Role role;

    public UserParams(String searchValue, String city, String country, Role role) {
        this.searchValue = searchValue;
        this.city = city;
        this.country = country;
        this.role = role;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
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

    public Role getUserType() {
        return role;
    }

    public void setUserType(Role role) {
        this.role = role;
    }
}
