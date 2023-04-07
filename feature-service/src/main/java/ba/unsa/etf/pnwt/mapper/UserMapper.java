package ba.unsa.etf.pnwt.mapper;

import ba.unsa.etf.pnwt.dto.UserDTO;
import ba.unsa.etf.pnwt.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToProjection(UserEntity userEntity){
        UserDTO projection = new UserDTO();
        projection.setId(userEntity.getId());
        projection.setUuid(userEntity.getUuid());
        projection.setEmail(userEntity.getEmail());
        return projection;
    }

    public static List<UserDTO> mapToProjections(List<UserEntity> entities) {
        return entities.stream().map(UserMapper::mapToProjection).collect(Collectors.toList());
    }

    public static UserEntity mapToEntity(UserDTO user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUuid(user.getUuid());
        entity.setEmail(user.getEmail());
        return entity;
    }
}
