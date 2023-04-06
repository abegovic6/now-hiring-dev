package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.constants.UserType;
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

    @Override
    public List<UserDTO> getAllUsers(UserParams userParams) {
        List<UserEntity> entities;

        if (userParams.getSearchValue() != null) {
            entities = userRepository.findAllByCompanyNameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
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
    public UserDTO createUser(UserDTO userDTO) {
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
        userDTO = UserMapper.mapToProjection(userRepository.save(userEntity));

        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String uuid, UserType userType) {
        UserEntity userEntity = getUserEntityByUUID(uuid);

        if (!userType.equals(userEntity.getUserType())) {
            throw new NotValidException("Not a " + userType + " profile!");
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
        UserEntity userEntity = userRepository.findUserEntityByUuid(uuid);
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
        UserEntity userEntity = userRepository.findUserEntityById(id);
        if (userEntity == null) {
            throw new EntityNotFoundException(ApiResponseMessages.USER_NOT_FOUND_WITH_ID);
        }
        return userEntity;
    }
}
