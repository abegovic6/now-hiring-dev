package ba.unsa.etf.pnwt.mapper;

import ba.unsa.etf.pnwt.dto.SkillDTO;
import ba.unsa.etf.pnwt.dto.UserDTO;
import ba.unsa.etf.pnwt.entity.SkillEntity;
import ba.unsa.etf.pnwt.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SkillMapper {
    public static SkillDTO mapToProjection(SkillEntity skillEntity){
        SkillDTO projection = new SkillDTO();
        projection.setId(skillEntity.getId());
        projection.setUser(UserMapper.mapToProjection(skillEntity.getUser()));
        projection.setTitle(skillEntity.getTitle());
        return projection;
    }

    public static List<SkillDTO> mapToProjections(List<SkillEntity> entities) {
        return entities.stream().map(SkillMapper::mapToProjection).collect(Collectors.toList());
    }

    public static SkillEntity mapToEntity(SkillDTO skillDTO){
        SkillEntity entity = new SkillEntity();
        entity.setId(skillDTO.getId());
        entity.setUser(UserMapper.mapToEntity(skillDTO.getUser()));
        entity.setTitle(skillDTO.getTitle());
        return entity;
    }
}
