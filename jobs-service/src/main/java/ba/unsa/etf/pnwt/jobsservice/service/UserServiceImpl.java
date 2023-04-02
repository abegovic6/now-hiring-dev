package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import ba.unsa.etf.pnwt.jobsservice.exceptions.NotValidException;
import ba.unsa.etf.pnwt.jobsservice.mapper.JobMapper;
import ba.unsa.etf.pnwt.jobsservice.mapper.UserMapper;
import ba.unsa.etf.pnwt.jobsservice.repository.JobRepository;
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


}
