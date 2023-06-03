package ba.unsa.etf.pnwt.service.user;

import ba.unsa.etf.pnwt.dto.UserDTO;
import ba.unsa.etf.pnwt.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserByEmail(String email);

    UserDTO getUserById(Integer id);

    UserDTO getUserByUuid(String uuid);

    UserEntity crateUser(UserDTO userDTO);

    String deleteUser(String uuid);

    UserDTO editUser(UserDTO userDTO);
}
