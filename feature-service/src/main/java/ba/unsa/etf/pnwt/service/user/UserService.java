package ba.unsa.etf.pnwt.service.user;

import ba.unsa.etf.pnwt.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Integer id);

    UserDTO getUserByUuid(String uuid);

    UserDTO crateUser(UserDTO userDTO);

    String deleteUser(String uuid);

    UserDTO editUser(UserDTO userDTO);
}
