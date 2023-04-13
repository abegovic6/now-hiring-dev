package ba.unsa.etf.pnwt.jobsservice.mapper;

import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapps example related data
 */
public class JobMapper {

    public static JobDTO mapToProjection(JobEntity entity) {
        JobDTO projection = new JobDTO();
        projection.setId(entity.getId());
        projection.setTitle(entity.getTitle());
        projection.setLocation(entity.getLocation());
        projection.setValidTo(entity.getValidTo());
        projection.setType(entity.getType());
        projection.setDescription(entity.getDescription());
        projection.setCompanyId(entity.getCompanyId());
        projection.setExpired(entity.getValidTo().isBefore(LocalDate.now()));
        return projection;
    }

    public static JobDTO mapToProjection(JobEntity entity, UserEntity userEntity) {
        JobDTO projection = mapToProjection(entity);
        projection.setCompanyName(userEntity.getCompanyName());
        return projection;
    }

    public static List<JobDTO> mapToProjections(List<JobEntity> entities) {
        return entities.stream().map(JobMapper::mapToProjection).collect(Collectors.toList());
    }

    public static List<JobDTO> mapToProjections(List<JobEntity> entities, UserEntity userEntity) {
        return entities.stream().map(jobEntity -> mapToProjection(jobEntity, userEntity)).collect(Collectors.toList());
    }

    public static JobEntity mapToEntity(JobDTO projection){
        if (projection == null) return null;

        JobEntity entity = new JobEntity();
        entity.setId(projection.getId());
        entity.setDescription(projection.getDescription());
        entity.setTitle(projection.getTitle());
        entity.setType(projection.getType());
        entity.setLocation(projection.getLocation());
        entity.setCompanyId(projection.getCompanyId());
        if(projection.getValidTo() != null)
            entity.setValidTo(LocalDate.parse(projection.getValidTo()));
        else entity.setValidTo(null);
        return entity;
    }
}
