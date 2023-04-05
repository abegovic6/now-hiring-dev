package ba.unsa.etf.pnwt.jobsservice.mapper;

import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapps example related data
 */
public class UserMapper {

    public static UserDTO mapToProjection(UserEntity entity) {
        UserDTO projection = new UserDTO();
        projection.setUid(entity.getUid());
        projection.setUserType(entity.getUserType());
        projection.setFirstName(entity.getFirstName());
        projection.setLastName(entity.getLastName());
        projection.setCompanyName(entity.getCompanyName());
        projection.setDescription(entity.getDescription());
        projection.setLocation(entity.getLocation());
        projection.setEmail(entity.getEmail());
        projection.setId(entity.getId());
        return projection;
    }

    public static List<UserDTO> mapToProjections(List<UserEntity> entities) {
        return entities.stream().map(UserMapper::mapToProjection).collect(Collectors.toList());
    }

    public static UserEntity mapToEntity(UserDTO projection){
        if (projection == null) return null;

        UserEntity entity = new UserEntity();
        entity.setUserType(projection.getUserType());
        entity.setUid(projection.getUid());
        entity.setLocation(projection.getLocation());
        entity.setDescription(projection.getDescription());
        entity.setFirstName(projection.getFirstName());
        entity.setLastName(projection.getLastName());
        entity.setCompanyName(projection.getCompanyName());
        entity.setEmail(projection.getEmail());
        entity.setId(projection.getId());

        return entity;
    }
}
