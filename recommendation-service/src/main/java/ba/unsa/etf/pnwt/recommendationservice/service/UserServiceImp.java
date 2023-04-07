package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.exceptions.ApiRequestException;
import ba.unsa.etf.pnwt.recommendationservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getUser() {
        return userRepository.findAll();
    }

    @Override
    public void addNewUser(UserEntity user) {
        Optional<UserEntity> userEntityByEmail = userRepository.findUserEntityByEmail(user.getEmail());
        if(userEntityByEmail.isPresent()){
            throw new ApiRequestException("Email already taken");
        }
        userRepository.save(user);
        return;
    }

    @Override
    public void deleteUser(Long id) {
//        Optional<UserEntity> deletedUser = userRepository.findById(id);
//        userRepository.deleteById(id);
        boolean exists = userRepository.existsById(id);
        if(!exists){
            throw new ApiRequestException("User with id "+ id +" doesn't exists");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserEntity updateUser(Long id, String name, String email) {
        UserEntity updatedUser = userRepository.findById(id).orElseThrow(
                ()->new ApiRequestException("User with id" + id + " doesn't exist")
        );

        if(name != null && !updatedUser.getName().equals(name)){
            updatedUser.setName(name);
        }
        if(email != null && !updatedUser.getEmail().equals(email)){
            if(userRepository.findUserEntityByEmail(email).isPresent()){
                throw new ApiRequestException("User with email "+email+" already exists");
            }
            updatedUser.setEmail(email);
        }

        return updatedUser;

    }

    @Override
    public UserEntity deleteUserByEmail(String email) {
        Optional<UserEntity> userEntityByEmail = userRepository.findUserEntityByEmail(email);
        if(!userEntityByEmail.isPresent()){
            throw new ApiRequestException("User with provided email " + email + " doesn't exist!");
        }
        List<Optional<JobEntity>> jobByCreator = userRepository.findJobByCreator(email);

        if(!jobByCreator.isEmpty()){
            throw new ApiRequestException("User with email "+email+" can't be deleted because it's used in other table!");
        }
        userRepository.deleteById(userEntityByEmail.get().getId());
        return userEntityByEmail.get();
    }
}
