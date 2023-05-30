package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.constants.Role;
import ba.unsa.etf.pnwt.userservice.controller.UserController;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.entity.LocationEntity;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import ba.unsa.etf.pnwt.userservice.exception.NotValidException;
import ba.unsa.etf.pnwt.userservice.mapper.UserMapper;
import ba.unsa.etf.pnwt.userservice.params.UserParams;
import ba.unsa.etf.pnwt.userservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The service class for {@link UserController}
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired protected UserRepository userRepository;
    @Autowired protected LocationService locationService;
    @Autowired protected UUIDService uuidService;
    @Autowired protected EmailService emailService;

    @Override
    public List<UserDTO> getAllUsers(UserParams userParams) {
        List<UserEntity> entities;

        if (userParams.getSearchValue() != null) {
            entities = userRepository.findAllByCompanyNameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseAndVerifiedIsTrue(
                    userParams.getSearchValue(), userParams.getSearchValue(), userParams.getSearchValue());
        } else {
            entities = userRepository.findAll();
        }

        entities = entities.stream().filter(user -> {
                    boolean containing = userParams.getUserType() == null || user.getUserType().equals(userParams.getUserType());
                    if (userParams.getCity() != null && !user.getLocationEntity().getCity().equals(userParams.getCity())) {
                        containing = false;
                    }
                    if (userParams.getCountry() != null && !user.getLocationEntity().getCountry().equals(userParams.getCountry())) {
                        containing = false;
                    }
                    return containing;
                }

        ).collect(Collectors.toList());

        return UserMapper.mapToProjections(entities);
    }

    @Override
    public UserDTO getUserById(int id) {
        return UserMapper.mapToProjection(getUserEntityById(id));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserMapper.mapToProjection(getUserEntityByEmail(email));
    }

    @Override
    public UserDTO getUserByUUID(String uuid) {
        return UserMapper.mapToProjection(getUserEntityByUUID(uuid));
    }

    @Override
    public UserDTO verifyUser(String email, String code) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity.isVerified()) {
            throw new NotValidException(ApiResponseMessages.USER_IS_ALREADY_VERIFIED);
        }
        if (!code.equals(userEntity.getCode())) {
            throw new NotValidException(ApiResponseMessages.WRONG_VERIFICATION_CODE);
        }
        userEntity.setVerified(true);
        userRepository.save(userEntity);
        return getUserByEmail(email);
    }

    @Override
    public UserEntity createUser(UserDTO userDTO) {
        if (userDTO.getLocationDTO() == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_CITY);
        }
        if (userDTO.getLocationDTO().getCity() == null) {
            throw new NotValidException(ApiResponseMessages.MISSING_CITY);
        }
        if (userDTO.getLocationDTO().getCountry() == null){
            throw new NotValidException(ApiResponseMessages.MISSING_COUNTRY);
        }

        LocationEntity locationEntity =
                locationService.getLocationOrCreateIfMissing(userDTO.getLocationDTO().getCity(), userDTO.getLocationDTO().getCountry());
        UserEntity userEntity = UserMapper.mapToEntity(userDTO, null);
        userEntity.setUuid(uuidService.createNewUUID());
        userEntity.setLocationEntity(locationEntity);
        userEntity.setVerified(false);
        setCodeAndSend(userEntity);
        return userRepository.save(userEntity);
    }

    @Override
    public void sendCodeAgain(String email) {
        UserEntity userEntity = getUserEntityByEmail(email);
        setCodeAndSend(userEntity);
    }

    @Override
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        UserEntity userEntity = getUserEntityByEmail(email);
        if (!userEntity.comparePasswords(password)) {
            throw new NotValidException(ApiResponseMessages.WRONG_EMAIL_OR_PASSWORD);
        }
        return UserMapper.mapToProjection(userEntity);
    }

    @Override
    public Mono<UserDTO> getMonoUserByEmail(String email) {
        return null;
    }

    private void setCodeAndSend(UserEntity userEntity) {
        String code = UserMapper.getRandomCode();
        userEntity.setCode(code);

        emailService.sendEmail("Verification code", "Your verification code is " +  code, userEntity);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String uuid, Role role) {
        UserEntity userEntity = getUserEntityByUUID(uuid);

        if (!role.equals(userEntity.getUserType())) {
            throw new NotValidException("Not a " + role + " profile!");
        }

        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setCompanyName(userDTO.getCompanyName());
        userEntity.setDescription(userDTO.getDescription());
        if (userDTO.getLocationDTO() != null) {
            if (userDTO.getLocationDTO().getCity() == null) {
                throw new NotValidException(ApiResponseMessages.MISSING_CITY);
            }
            if (userDTO.getLocationDTO().getCountry() == null) {
                throw new NotValidException(ApiResponseMessages.MISSING_COUNTRY);
            }
            userEntity.setLocationEntity(
                    locationService.getLocationOrCreateIfMissing
                            (userDTO.getLocationDTO().getCity(), userDTO.getLocationDTO().getCountry())
            );
        }
        userRepository.save(userEntity);

        return getUserByUUID(uuid);
    }

    @Override
    public UserDTO updatePassword(String uuid, String oldPassword, String newPassword) {
        UserEntity userEntity = getUserEntityByUUID(uuid);
        if (!userEntity.comparePasswords(oldPassword)) {
            throw new NotValidException(ApiResponseMessages.PASSWORDS_DO_NOT_MATCH);
        }

        userEntity.setPassword(oldPassword);
        userEntity.setModificationTS(ZonedDateTime.now());
        userRepository.save(userEntity);
        return getUserByUUID(uuid);
    }

    @Override
    public UserEntity getUserEntityByUUID(String uuid) {
        UserEntity userEntity = userRepository.findUserEntityByUuidAndVerifiedIsTrue(uuid);
        if (userEntity == null) {
            throw new EntityNotFoundException(ApiResponseMessages.USER_NOT_FOUND_WITH_UUID);
        }
        return userEntity;
    }

    @Override
    public UserEntity getUserEntityByEmail(String email) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity == null) {
            throw new EntityNotFoundException(ApiResponseMessages.USER_NOT_FOUND_WITH_EMAIL);
        }
        return userEntity;
    }

    @Override
    public UserEntity getUserEntityById(int id) {
        UserEntity userEntity = userRepository.findUserEntityByIdAndVerifiedIsTrue(id);
        if (userEntity == null) {
            throw new EntityNotFoundException(ApiResponseMessages.USER_NOT_FOUND_WITH_ID);
        }
        return userEntity;
    }
}
