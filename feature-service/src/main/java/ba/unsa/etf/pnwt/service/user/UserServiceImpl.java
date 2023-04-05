package ba.unsa.etf.pnwt.service.user;

import ba.unsa.etf.pnwt.dto.UserDTO;
import ba.unsa.etf.pnwt.mapper.UserMapper;
import ba.unsa.etf.pnwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public List<UserDTO> getAllUsers() {
        return UserMapper.mapToProjections(userRepository.findAll());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserMapper.mapToProjection(userRepository.findByEmail(email));
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return UserMapper.mapToProjection(userRepository.findById(id));
    }

    @Override
    public UserDTO getUserByUuid(String uuid) {
        return UserMapper.mapToProjection(userRepository.findByUuid(uuid));
    }


    @Override
    public UserDTO crateUser(UserDTO userDTO) {
        return UserMapper.mapToProjection(userRepository.save(UserMapper.mapToEntity(userDTO)));
    }

    @Override
    public String deleteUser(String uuid){
        userRepository.deleteByUuid(uuid);
        return "Succes";
    }

    @Override
    public UserDTO editUser(UserDTO userDTO){
        return UserMapper.mapToProjection(userRepository.save(UserMapper.mapToEntity(userDTO)));
    }
}
