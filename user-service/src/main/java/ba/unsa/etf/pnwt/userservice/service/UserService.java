package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(int id);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByUUID(String uuid);

    ResponseEntity<String> createUser(UserDTO userDTO);
    ResponseEntity<String> updateUser(UserDTO userDTO);
}
