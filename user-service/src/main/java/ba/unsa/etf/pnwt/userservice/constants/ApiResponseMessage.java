package ba.unsa.etf.pnwt.userservice.constants;

import org.springframework.http.ResponseEntity;

public class ApiResponseMessage {
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_DESCRIPTION_LENGTH = 255;
    public static final int PASSWORD_MAX_LENGTH = 25;
    public static final int PASSWORD_MIN_LENGTH = 8;

    // successful
    public static final String USER_CREATED = "The user was successfully created!";
    public static final String CITY_CREATED = "The city was successfully created!";
    public static final String COUNTRY_CREATED = "The country was successfully created!";


    // missing data
    public static final String MISSING_EMAIL = "Missing email. Please provide.";
    public static final String MISSING_COUNTRY = "Missing country name. Please provide.";
    public static final String MISSING_CITY = "Missing city name. Please provide.";
    public static final String MISSING_ACCOUNT_TYPE = "Missing account type. Please provide.";
    public static final String MISSING_PASSWORD = "Missing password. Please provide.";

    // wrong data
    public static final String COUNTRY_DOES_NOT_EXISTS = "The provided county does not exist";
    public static final String CITY_DOES_NOT_EXISTS = "The provided city does not exist";
    public static final String CITY_DOES_NOT_MATCH_THE_COUNTRY = "The provided city does not match the provided country";


    // wrong format
    public static final String EMAIL_HAS_WRONG_FORMAT = "The provided email has a wrong format. Follow the next patter: email@mail.com";
    public static final String EMAIL_TOO_LONG = "The provided email is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String CITY_NAME_TOO_LONG = "The provided city name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String COUNTRY_NAME_TOO_LONG = "The provided county name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String FIRST_NAME_TOO_LONG = "The provided first name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String LAST_NAME_TOO_LONG = "The provided last name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String NAME_TOO_LONG = "The provided account name is too long. Tha max allowed characters: " + MAX_NAME_LENGTH;
    public static final String DESCRIPTION_TO_LONG = "The provided description is too long. Tha max allowed characters: " + MAX_DESCRIPTION_LENGTH;
    public static final String CAN_NOT_DECLARE_NAME_WITH_PRIVATE_ACCOUNT = "The data is not matching. Can not add a name to a private account. Please define first and last name";
    public static final String CAN_NOT_DECLARE_NAME_WITH_COMPANY_ACCOUNT = "The data is not matching. Can not add a first or last name to a company account. Please define account name";
    public static final String PASSWORD_LENGTH = "The password should be in between " + PASSWORD_MIN_LENGTH + " and " + PASSWORD_MAX_LENGTH;
    public static final String PASSWORD_TO_WEAK = "The password is too weak. Provide a password with at least one uppercase, one lowercase letter and one number.";


    public static ResponseEntity<String> createOkResponse(String message) {
        return ResponseEntity.ok().body(message);
    }

    public static ResponseEntity<String> createBadRequestResponse(String message) {
        return ResponseEntity.badRequest().body(message);
    }
}
