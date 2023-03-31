package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getByUid(String uid);


}
