package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.mapper.UserMapper;
import ba.unsa.etf.pnwt.userservice.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    protected UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return UserMapper.mapToProjections(userRepository.findAll());
    }

    @Override
    public String uploadDummyUsers() {
        UserDTO userDTO = new UserDTO();

        return "UPLOADED";
    }
}
