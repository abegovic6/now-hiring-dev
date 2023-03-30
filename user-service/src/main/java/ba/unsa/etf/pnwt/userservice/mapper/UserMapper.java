package ba.unsa.etf.pnwt.userservice.mapper;

import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToProjection(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDTO projection = new UserDTO();
        projection.setUuid(entity.getUuid());
        projection.setCity(CityMapper.mapToProjection(entity.getCityEntity()));
        projection.setFirstName(entity.getFirstName());
        projection.setLastName(entity.getLastName());
        projection.setCity(CityMapper.mapToProjection(entity.getCityEntity()));
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
}
