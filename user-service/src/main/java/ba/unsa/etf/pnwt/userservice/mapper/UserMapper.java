package ba.unsa.etf.pnwt.userservice.mapper;

import ba.unsa.etf.pnwt.userservice.constants.UserType;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToProjection(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDTO projection = new UserDTO();
        projection.setId(entity.getId());
        projection.setUuid(entity.getUuid());
        projection.setLocationDTO(LocationMapper.mapToProjection(entity.getLocationEntity()));

        projection.setFirstName(entity.getFirstName());
        projection.setLastName(entity.getLastName());
        projection.setCompanyName(entity.getCompanyName());
        projection.setDescription(entity.getDescription());
        projection.setEmail(entity.getEmail());

        if (UserType.COMPANY.equals(entity.getUserType())) {
            projection.setDisplayValue(entity.getCompanyName());
        } else {
            projection.setDisplayValue(entity.getFirstName() + " " + entity.getLastName());
        }

        projection.setUserType(entity.getUserType());
        return projection;
    }

    public static List<UserDTO> mapToProjections(List<UserEntity> entities) {
        return entities.stream().map(UserMapper::mapToProjection).collect(Collectors.toList());
    }

    public static UserEntity mapToEntity(UserDTO projection, UserEntity entity) {
        if (projection == null) {
            return null;
        }

        if (entity == null) {
            entity = new UserEntity();
            entity.setCreationTS(ZonedDateTime.now());
        }

        entity.setFirstName(projection.getFirstName());
        entity.setLastName(projection.getLastName());
        entity.setCompanyName(projection.getCompanyName());
        entity.setUserType(projection.getUserType());
        entity.setDescription(projection.getDescription());
        entity.setEmail(projection.getEmail());
        entity.setPassword(projection.getPassword());
        return entity;
    }

    public static String getRandomCode() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
