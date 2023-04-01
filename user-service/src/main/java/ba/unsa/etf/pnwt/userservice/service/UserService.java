package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.UserType;
import ba.unsa.etf.pnwt.userservice.controller.UserController;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.params.UserParams;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * The service class for {@link UserController}
 */
public interface UserService {

    List<UserDTO> getAllUsers(UserParams userParams);

    UserDTO getUserById(int id);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByUUID(String uuid);

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO newUser, String uuid, UserType userType);
    UserDTO updateEmail(String oldEmail, String newEmail);
    UserDTO updatePassword(String uuid, String oldPassword, String newPassword);
}
