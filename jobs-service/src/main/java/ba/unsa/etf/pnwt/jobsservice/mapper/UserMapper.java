package ba.unsa.etf.pnwt.jobsservice.mapper;

import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapps example related data
 */
public class UserMapper {

    public static UserDTO mapToProjection(UserEntity entity) {
        UserDTO projection = new UserDTO();
        projection.setId(entity.getUid());
        projection.setUserType(entity.getUserType());
        projection.setFirstName(entity.getFirstName());
        projection.setLastName(entity.getLastName());
        projection.setCompanyName(entity.getCompanyName());
        projection.setDescription(entity.getDescription());
        projection.setLocation(entity.getLocation());
        return projection;
    }

    public static List<UserDTO> mapToProjections(List<UserEntity> entities) {
        return entities.stream().map(UserMapper::mapToProjection).collect(Collectors.toList());
    }
}
