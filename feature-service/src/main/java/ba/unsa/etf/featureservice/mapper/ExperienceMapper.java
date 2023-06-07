package ba.unsa.etf.featureservice.mapper;

import ba.unsa.etf.featureservice.dto.ExperienceDTO;
import ba.unsa.etf.featureservice.entity.ExperienceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ExperienceMapper {
    public static ExperienceDTO mapToProjection(ExperienceEntity experienceEntity){
        ExperienceDTO projection = new ExperienceDTO();
        projection.setId(experienceEntity.getId());
        projection.setDescription(experienceEntity.getDescription());
        projection.setEndMonth(experienceEntity.getEndMonth());
        projection.setEndYear(experienceEntity.getEndYear());
        projection.setStartingMonth(experienceEntity.getStartingMonth());
        projection.setStartingYear(experienceEntity.getStartingYear());
        projection.setTitle(experienceEntity.getTitle());
        projection.setUser(UserMapper.mapToProjection(experienceEntity.getUser()));
        return projection;
    }

    public static List<ExperienceDTO> mapToProjections(List<ExperienceEntity> entities) {
        return entities.stream().map(ExperienceMapper::mapToProjection).collect(Collectors.toList());
    }

    public static ExperienceEntity mapToEntity(ExperienceDTO experienceDTO) {
        ExperienceEntity experienceEntity = new ExperienceEntity();
        experienceEntity.setId(experienceDTO.getId());
        experienceEntity.setTitle(experienceDTO.getTitle());
        experienceEntity.setDescription(experienceDTO.getDescription());
        experienceEntity.setEndMonth(experienceDTO.getEndMonth());
        experienceEntity.setEndYear(experienceDTO.getEndYear());
        experienceEntity.setStartingMonth(experienceDTO.getStartingMonth());
        experienceEntity.setStartingYear(experienceDTO.getStartingYear());
        experienceEntity.setUser(UserMapper.mapToEntity(experienceDTO.getUser()));
        return experienceEntity;
    }
}
