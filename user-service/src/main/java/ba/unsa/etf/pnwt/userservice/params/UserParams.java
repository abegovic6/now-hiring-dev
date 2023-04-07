package ba.unsa.etf.pnwt.userservice.params;

import ba.unsa.etf.pnwt.userservice.constants.UserType;

public class UserParams {
    private String searchValue;
    private String city;
    private String country;
    private UserType userType;

    public UserParams(String searchValue, String city, String country, UserType userType) {
        this.searchValue = searchValue;
        this.city = city;
        this.country = country;
        this.userType = userType;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
