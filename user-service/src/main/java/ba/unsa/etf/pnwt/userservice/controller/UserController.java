package ba.unsa.etf.pnwt.userservice.controller;


import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.constants.UserType;
import ba.unsa.etf.pnwt.userservice.dto.PasswordDTO;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.exception.NotValidException;
import ba.unsa.etf.pnwt.userservice.params.UserParams;
import ba.unsa.etf.pnwt.userservice.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    protected UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.ALL_USERS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))})}
    )
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll(
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) UserType userType
    ) {
        return new ResponseEntity<>(userService.getAllUsers(
                new UserParams(searchValue, city, country, userType)
        ), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_WAS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = ApiResponseMessages.USER_NOT_FOUND_WITH_ID,
                    content = @Content)})
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_WAS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = ApiResponseMessages.USER_NOT_FOUND_WITH_EMAIL,
                    content = @Content)})
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_WAS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = ApiResponseMessages.USER_NOT_FOUND_WITH_UUID,
                    content = @Content)})
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<UserDTO> getUserByUUID(@PathVariable("uuid") String uuid) {
        return new ResponseEntity<>(userService.getUserByUUID(uuid), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_WAS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = ApiResponseMessages.WRONG_EMAIL_OR_PASSWORD,
                    content = @Content)})
    @GetMapping("{email}/authenticate")
    public ResponseEntity<UserDTO> getUserByEmailAndPassword(
            @PathVariable("email") String email,
            @RequestBody PasswordDTO password) {
        return new ResponseEntity<>(userService.getUserByEmailAndPassword(email, password.getOldPassword()), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_WAS_VERIFIED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = ApiResponseMessages.USER_NOT_FOUND_WITH_EMAIL,
                    content = @Content)})
    @PutMapping("/{email}/verify")
    public ResponseEntity<UserDTO> verifyUser(@PathVariable("email") String email, @RequestParam("code") String code) {
        return new ResponseEntity<>(userService.verifyUser(email, code), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiResponseMessages.VERIFICATION_CODE_WAS_SENT,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})})
    @PostMapping("/upload")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        validateUserCreation(userDTO);
        userService.createUser(userDTO);
        return new ResponseEntity<>(ApiResponseMessages.VERIFICATION_CODE_WAS_SENT, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiResponseMessages.VERIFICATION_CODE_WAS_SENT,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))})})
    @PutMapping("/{email}/sendCodeAgain")
    public ResponseEntity<String> sendVerificationCodeAgain(@PathVariable("email") String email) {
        userService.sendCodeAgain(email);
        return new ResponseEntity<>(ApiResponseMessages.VERIFICATION_CODE_WAS_SENT, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.USER_UPDATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))})})
    @PutMapping("/{uuid}/update/{userType}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("uuid") String uuid,
            @PathVariable("userType") UserType userType,
            @RequestBody UserDTO userDTO) {
        validateUserUpdate(userDTO, uuid, userType);
        return new ResponseEntity<>(userService.updateUser(userDTO, uuid, userType), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.PASSWORD_SUCCESSFULLY_CHANGED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))})})
    @PutMapping("/{uuid}/passwordUpdate")
    public ResponseEntity<UserDTO> updateUserPassword(
            @PathVariable("uuid") String uuid,
            @RequestBody PasswordDTO password) {
        validatePassword(password.getNewPassword());
        return new ResponseEntity<>(userService.updatePassword(uuid, password.getOldPassword(), password.getNewPassword()), HttpStatus.OK);
    }

    private void validateUserUpdate(UserDTO userDTO, String uuid, UserType userType) {
        if (userDTO.getUuid() == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_UUID);
        }
        if (!userDTO.getUuid().equals(uuid)) {
            throw new NotValidException(ApiResponseMessages.UUIDS_DO_NOT_MATCH);
        }
        validateUserTypeAndBasicData(userDTO, userType);
    }

    private void validateUserCreation(UserDTO userDTO) {
        if (userDTO.getUserType() == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_ACCOUNT_TYPE);
        }

        validateEmail(userDTO.getEmail());
        validatePassword(userDTO.getPassword());
        validateUserTypeAndBasicData(userDTO, userDTO.getUserType());
    }

    private void validateUserTypeAndBasicData(UserDTO userDTO, UserType userType) {
        if (userDTO.getDescription() != null && userDTO.getDescription().length() > ApiResponseMessages.MAX_DESCRIPTION_LENGTH) {
            throw new NotValidException(ApiResponseMessages.DESCRIPTION_TO_LONG);
        }
        if (userDTO.getCompanyName() != null) {
            if (userType.equals(UserType.PRIVATE)) {
                throw new NotValidException(ApiResponseMessages.CAN_NOT_DECLARE_NAME_WITH_PRIVATE_ACCOUNT);
            }
            if (userDTO.getCompanyName().length() > ApiResponseMessages.MAX_NAME_LENGTH) {
                throw new NotValidException(ApiResponseMessages.NAME_TOO_LONG);
            }
        }
        if (userDTO.getFirstName() != null) {
            if (userType.equals(UserType.COMPANY)) {
                throw new NotValidException(ApiResponseMessages.CAN_NOT_DECLARE_NAME_WITH_COMPANY_ACCOUNT);
            }
            if (userDTO.getFirstName().length() > ApiResponseMessages.MAX_NAME_LENGTH) {
                throw new NotValidException(ApiResponseMessages.FIRST_NAME_TOO_LONG);
            }
        }
        if (userDTO.getLastName() != null) {
            if (userType.equals(UserType.COMPANY)) {
                throw new NotValidException(ApiResponseMessages.CAN_NOT_DECLARE_NAME_WITH_COMPANY_ACCOUNT);
            }
            if (userDTO.getLastName().length() > ApiResponseMessages.MAX_NAME_LENGTH) {
                throw new NotValidException(ApiResponseMessages.LAST_NAME_TOO_LONG);
            }
        }
    }

    private void validateEmail(String email) {
        if (email == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_EMAIL);
        }
        if (email.length() > ApiResponseMessages.MAX_NAME_LENGTH) {
            throw new NotValidException(ApiResponseMessages.EMAIL_TOO_LONG);
        }

        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new NotValidException(ApiResponseMessages.EMAIL_HAS_WRONG_FORMAT);
        }
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_PASSWORD);
        }
        if (password.length() < 8 || password.length() > 25) {
            throw new NotValidException(ApiResponseMessages.PASSWORD_LENGTH);
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,25}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new NotValidException(ApiResponseMessages.PASSWORD_TO_WEAK);
        }
    }

}
