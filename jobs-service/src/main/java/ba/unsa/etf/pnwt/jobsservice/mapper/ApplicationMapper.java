package ba.unsa.etf.pnwt.jobsservice.mapper;

import ba.unsa.etf.pnwt.jobsservice.dto.ApplicationDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.ApplicationEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapps example related data
 */
public class ApplicationMapper {

    public static ApplicationDTO mapToProjection(ApplicationEntity entity) {
        ApplicationDTO projection = new ApplicationDTO();
        projection.setId(entity.getId());
        projection.setCoverLetter(entity.getCoverLetter());
        projection.setUserId(entity.getUserUid());
        return projection;
    }

    public static ApplicationDTO mapToProjection(ApplicationEntity entity, UserEntity userEntity) {
        ApplicationDTO projection = new ApplicationDTO();
        projection.setId(entity.getId());
        projection.setCoverLetter(entity.getCoverLetter());
        projection.setUserId(entity.getUserUid());
        projection.setFirstName(userEntity.getFirstName());
        projection.setLastName(userEntity.getLastName());
        projection.setLocation(userEntity.getLocation());
        projection.setDescription(userEntity.getDescription());
        return projection;
    }

    public static ApplicationDTO addJobToProjection(ApplicationDTO projection, Integer jobId){
        projection.setJobId(jobId);
        return projection;
    }

    public static List<ApplicationDTO> mapToProjections(List<ApplicationEntity> entities) {
        return entities.stream().map(ApplicationMapper::mapToProjection).collect(Collectors.toList());
    }

    public static List<ApplicationDTO> mapToProjections(List<ApplicationEntity> entities, UserEntity userEntity) {
        return entities.stream().map(entity -> mapToProjection(entity, userEntity)).collect(Collectors.toList());
    }


    public static ApplicationEntity mapToEntity(ApplicationDTO projection, ApplicationEntity applicationEntity){
        if (projection == null) return null;
        if (applicationEntity == null) applicationEntity = new ApplicationEntity();

        applicationEntity.setId(projection.getId());
        applicationEntity.setUserUid(projection.getUserId());
        applicationEntity.setCoverLetter(projection.getCoverLetter());

        return applicationEntity;
    }
}
