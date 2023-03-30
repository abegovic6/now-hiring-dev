package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessage;
import ba.unsa.etf.pnwt.userservice.dto.CityDTO;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.entity.CityEntity;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import ba.unsa.etf.pnwt.userservice.mapper.UserMapper;
import ba.unsa.etf.pnwt.userservice.repository.CityRepository;
import ba.unsa.etf.pnwt.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired protected UserRepository userRepository;
    @Autowired protected CityService cityService;
    @Autowired protected UUIDService uuidService;

    @Override
    public List<UserDTO> getAllUsers() {
        return UserMapper.mapToProjections(userRepository.findAll());
    }

    @Override
    public UserDTO getUserById(int id) {
        return UserMapper.mapToProjection(userRepository.findUserEntityById(id));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserMapper.mapToProjection(userRepository.findUserEntityByEmail(email));
    }

    @Override
    public UserDTO getUserByUUID(String uuid) {
        return UserMapper.mapToProjection(userRepository.findUserEntityByUuid(uuid));
    }

    @Override
    public ResponseEntity<String> createUser(UserDTO userDTO) {
        if (userDTO.getCity() == null) {
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.MISSING_CITY);
        }
        if (userDTO.getCity().getCountry() == null ){
            return ApiResponseMessage.createBadRequestResponse(ApiResponseMessage.MISSING_COUNTRY);
        }

        CityEntity cityEntity = cityService.getCityOrCreateIfMissing(userDTO.getCity().getName(), userDTO.getCity().getCountry().getName());
        UserEntity userEntity = UserMapper.mapToEntity(userDTO, null);
        userEntity.setUuid(uuidService.createNewUUID());
        userEntity.setCityEntity(cityEntity);
        userRepository.save(userEntity);

        return ApiResponseMessage.createOkResponse(ApiResponseMessage.USER_CREATED);
    }

    @Override
    public ResponseEntity<String> updateUser(UserDTO userDTO) {
        return null;
    }
}
