package ba.unsa.etf.featureservice.service.user;

import ba.unsa.etf.featureservice.dto.UserDTO;
import ba.unsa.etf.featureservice.entity.UserEntity;
import ba.unsa.etf.featureservice.mapper.UserMapper;
import ba.unsa.etf.featureservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null)
            return UserMapper.mapToProjection(userEntity);
        else throw new EntityNotFoundException("User with provided email not found");
    }

    @Override
    public UserDTO getUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity != null)
            return UserMapper.mapToProjection(userEntity);
        else throw new EntityNotFoundException("User with provided id not found");
    }

    @Override
    public UserDTO getUserByUuid(String uuid) {
        UserEntity userEntity = userRepository.findByUuid(uuid);
        if (userEntity != null)
            return UserMapper.mapToProjection(userEntity);
        else throw new EntityNotFoundException("User with provided uuid not found");
    }


    @Override
    public UserEntity crateUser(UserDTO userDTO) {
        //return UserMapper.mapToProjection(userRepository.save(UserMapper.mapToEntity(userDTO)));
        UserEntity userEntity =  UserMapper.mapToEntity(userDTO);
        return userRepository.save(userEntity);
    }

    @Override
    public String deleteUser(String uuid){
        UserEntity userEntity = userRepository.findByUuid(uuid);
        if (userEntity != null) {
            userRepository.deleteByUuid(uuid);
            return "Succes";
        }else throw new EntityNotFoundException("User with provided uuid not found");
    }

    @Override
    public UserDTO editUser(UserDTO userDTO){
        UserEntity userEntity = userRepository.findByUuid(userDTO.getUuid());
        if (userEntity != null)
            return UserMapper.mapToProjection(userRepository.save(UserMapper.mapToEntity(userDTO)));
        else throw new EntityNotFoundException("User with provided uuid not found");
    }
}
