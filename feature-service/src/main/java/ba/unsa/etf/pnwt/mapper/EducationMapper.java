package ba.unsa.etf.pnwt.mapper;

import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.dto.ExperienceDTO;
import ba.unsa.etf.pnwt.entity.EducationEntity;
import ba.unsa.etf.pnwt.entity.ExperienceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EducationMapper {
    public static EducationDTO mapToProjection(EducationEntity educationEntity){
        EducationDTO projection = new EducationDTO();
        projection.setId(educationEntity.getId());
        projection.setDescription(educationEntity.getDescription());
        projection.setEndMonth(educationEntity.getEndMonth());
        projection.setEndYear(educationEntity.getEndYear());
        projection.setStartingMonth(educationEntity.getStartingMonth());
        projection.setStartingYear(educationEntity.getStartingYear());
        projection.setTitle(educationEntity.getTitle());
        projection.setUser(UserMapper.mapToProjection(educationEntity.getUser()));
        return projection;
    }

    public static List<EducationDTO> mapToProjections(List<EducationEntity> entities) {
        return entities.stream().map(EducationMapper::mapToProjection).collect(Collectors.toList());
    }

    public static EducationEntity mapToEntity(EducationDTO educationDTO) {
        EducationEntity educationEntity = new EducationEntity();
        educationEntity.setId(educationDTO.getId());
        educationEntity.setTitle(educationDTO.getTitle());
        educationEntity.setDescription(educationDTO.getDescription());
        educationEntity.setEndMonth(educationDTO.getEndMonth());
        educationEntity.setEndYear(educationDTO.getEndYear());
        educationEntity.setStartingMonth(educationDTO.getStartingMonth());
        educationEntity.setStartingYear(educationDTO.getStartingYear());
        educationEntity.setUser(UserMapper.mapToEntity(educationDTO.getUser()));
        return educationEntity;
    }
}
