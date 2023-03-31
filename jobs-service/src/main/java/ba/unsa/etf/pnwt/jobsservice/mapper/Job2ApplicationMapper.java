package ba.unsa.etf.pnwt.jobsservice.mapper;

import ba.unsa.etf.pnwt.jobsservice.dto.Job2ApplicationDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.Job2ApplicationEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapps example related data
 */
public class Job2ApplicationMapper {

    public static Job2ApplicationDTO mapToProjection(Job2ApplicationEntity entity) {
        Job2ApplicationDTO projection = new Job2ApplicationDTO();
        projection.setId(entity.getId());
        projection.setApplicationId(entity.getApplicationId());
        projection.setJobId(entity.getJobId());
        return projection;
    }

    public static List<Job2ApplicationDTO> mapToProjections(List<Job2ApplicationEntity> entities) {
        return entities.stream().map(Job2ApplicationMapper::mapToProjection).collect(Collectors.toList());
    }
}
