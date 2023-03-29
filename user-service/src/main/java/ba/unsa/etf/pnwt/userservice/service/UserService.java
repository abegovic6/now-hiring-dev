package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import org.apache.coyote.Response;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    String uploadDummyUsers();
}
