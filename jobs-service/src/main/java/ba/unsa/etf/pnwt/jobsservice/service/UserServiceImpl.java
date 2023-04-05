package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import ba.unsa.etf.pnwt.jobsservice.exceptions.NotValidException;
import ba.unsa.etf.pnwt.jobsservice.mapper.UserMapper;
import ba.unsa.etf.pnwt.jobsservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAll() {
        return UserMapper.mapToProjections(userRepository.findAll());
    }

    @Override
    public UserDTO getByUid(String uid) {
        UserEntity userDb = userRepository.findUserEntityByUid(uid);
        if (userDb != null) return UserMapper.mapToProjection(userDb);
        else throw new EntityNotFoundException("User with provided ID not found");
    }

    @Override
    public String getUserType(String uid) {
        UserEntity userDb = userRepository.findUserEntityByUid(uid);
        if (userDb != null) return userDb.getUserType();
        else throw new EntityNotFoundException("User with provided ID not found");
    }

    @Override
    public String getCompanyName(String uid) {
        UserEntity userDb = userRepository.findUserEntityByUid(uid);
        if (userDb != null){
            if(Objects.equals(userDb.getUserType(), "COMPANY")) return userDb.getCompanyName();
            else throw new NotValidException("User with provided ID is not a company");
        }
        else throw new EntityNotFoundException("User with provided ID not found");
    }

    @Override
    public UserDTO save(UserDTO user) {
        UserEntity userDb = userRepository.findUserEntityByUid(user.getUid());
        if (userDb == null){
            return UserMapper.mapToProjection(userRepository.save(UserMapper.mapToEntity(user)));
        }
        else throw new NotValidException("User with provided ID already exists");
    }

    @Override
    public UserDTO update(UserDTO user) {
        UserEntity userDb = userRepository.findUserEntityByUid(user.getUid());
        if (userDb == null){
            throw new EntityNotFoundException("User not found");
        }
        else{
            if (Objects.equals(userDb.getUserType(), "PRIVATE")){
                if(user.getFirstName() == null) user.setFirstName(userDb.getFirstName());
                if(user.getLastName() == null) user.setLastName(userDb.getLastName());
                if(user.getDescription() == null) user.setDescription(userDb.getDescription());
                if(user.getLocation() == null) user.setLocation(userDb.getLocation());
                user.setCompanyName(null);
                user.setEmail(userDb.getEmail());
                user.setId(userDb.getId());
                user.setUserType(userDb.getUserType());
            }
            else if (Objects.equals(userDb.getUserType(), "COMPANY")){
                if(user.getCompanyName() == null) user.setCompanyName(userDb.getCompanyName());
                if(user.getLocation() == null) user.setLocation(userDb.getLocation());
                if(user.getDescription() == null) user.setDescription(userDb.getDescription());
                user.setFirstName(null);
                user.setLastName(null);
                user.setId(userDb.getId());
                user.setEmail(userDb.getEmail());
                user.setUserType(userDb.getUserType());
            }
            return UserMapper.mapToProjection(userRepository.save(UserMapper.mapToEntity(user)));
        }
    }

    @Override
    public String deleteById(String id) {
        UserEntity userDb = userRepository.findUserEntityByUid(id);
        if (userDb == null){
            throw new EntityNotFoundException("User not found");
        }
        else{
            userRepository.deleteById((long) userDb.getId());
            return "User successfully deleted";
        }
    }


}
