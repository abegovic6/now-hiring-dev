package ba.unsa.etf.pnwt.userservice.controller;


import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessage;
import ba.unsa.etf.pnwt.userservice.constants.UserType;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public UserDTO getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable("email") String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/uuid/{uuid}")
    public UserDTO getUserByUUID(@PathVariable("uuid") String uuid) {
        return userService.getUserByUUID(uuid);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        ResponseEntity<String> response = validateUser(userDTO);
        if (response == null) {
            response = userService.createUser(userDTO);
        }
        return response;
    }



    private ResponseEntity<String> validateUser(UserDTO userDTO) {
        if (userDTO.getUserType() == null) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.MISSING_ACCOUNT_TYPE);
        }
        if (userDTO.getEmail() == null) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.MISSING_EMAIL);
        }
        if (userDTO.getCompanyName() != null) {
            if (userDTO.getUserType().equals(UserType.PRIVATE)) {
                return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.CAN_NOT_DECLARE_NAME_WITH_PRIVATE_ACCOUNT);
            }
            if (userDTO.getCompanyName().length() > ApiResponseMessage.MAX_NAME_LENGTH) {
                return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.NAME_TOO_LONG);
            }
        }
        if (userDTO.getFirstName() != null) {
            if (userDTO.getUserType().equals(UserType.COMPANY)) {
                return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.CAN_NOT_DECLARE_NAME_WITH_COMPANY_ACCOUNT);
            }
            if (userDTO.getFirstName().length() > ApiResponseMessage.MAX_NAME_LENGTH) {
                return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.FIRST_NAME_TOO_LONG);
            }
        }
        if (userDTO.getLastName() != null) {
            if (userDTO.getUserType().equals(UserType.COMPANY)) {
                return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.CAN_NOT_DECLARE_NAME_WITH_COMPANY_ACCOUNT);
            }
            if (userDTO.getLastName().length() > ApiResponseMessage.MAX_NAME_LENGTH) {
                return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.LAST_NAME_TOO_LONG);
            }
        }

        if (userDTO.getDescription() != null && userDTO.getDescription().length() > ApiResponseMessage.MAX_DESCRIPTION_LENGTH) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.DESCRIPTION_TO_LONG);
        }

        return validateEmailAndPassword(userDTO);

    }

    private ResponseEntity<String> validateEmailAndPassword(UserDTO userDTO) {
        if (userDTO.getEmail().length() > ApiResponseMessage.MAX_NAME_LENGTH) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.EMAIL_TOO_LONG);
        }

        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(userDTO.getEmail());
        if (!matcher.matches()) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.EMAIL_HAS_WRONG_FORMAT);
        }

        if (userDTO.getPassword() == null) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.MISSING_PASSWORD);
        }
        if (userDTO.getPassword().length() < 8 || userDTO.getPassword().length() > 25) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.PASSWORD_LENGTH);
        }
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,25}$";
        pattern = Pattern.compile(passwordRegex);
        matcher = pattern.matcher(userDTO.getPassword());
        if (!matcher.matches()) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.PASSWORD_TO_WEAK);
        }


        return null;
    }

}
