package ba.unsa.etf.featureservice.service.education;

import ba.unsa.etf.featureservice.entity.EducationEntity;
import ba.unsa.etf.featureservice.entity.UserEntity;
import ba.unsa.etf.featureservice.repository.UserRepository;
import ba.unsa.etf.featureservice.dto.EducationDTO;
import ba.unsa.etf.featureservice.mapper.EducationMapper;
import ba.unsa.etf.featureservice.repository.EducationRepository;
import ba.unsa.etf.pnwt.proto.LoggingRequest;
import ba.unsa.etf.pnwt.proto.LoggingServiceGrpc;
import jakarta.persistence.EntityNotFoundException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService{

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private UserRepository userRepository;

    @GrpcClient("logging")
    LoggingServiceGrpc.LoggingServiceBlockingStub loggingServiceBlockingStub;

    @Override
    public List<EducationDTO> findEducationByUser(Integer id){
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity != null){
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("FeatureService")
                    .setControllerName("EducationController")
                    .setActionUrl("/feature-service/user/" + id)
                    .setActionType("GET")
                    .setActionResponse("SUCCESS")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            return EducationMapper.mapToProjections(educationRepository.findByUserId(id));
        }
        else {
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("FeatureService")
                    .setControllerName("EducationController")
                    .setActionUrl("/feature-service/user/" + id)
                    .setActionType("GET")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new EntityNotFoundException("User with provided id not found");
        }
    }

    @Override
    public EducationDTO createEducation(EducationDTO educationDTO) {
        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("FeatureService")
                .setControllerName("EducationController")
                .setActionUrl("/feature-service/education/add")
                .setActionType("POST")
                .setActionResponse("SUCCESS")
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        return EducationMapper.mapToProjection(educationRepository.save(EducationMapper.mapToEntity(educationDTO)));
    }

    @Override
    public String deleteEducation(Integer id) {
        Optional<EducationEntity> educationEntity = educationRepository.findById(id);
        if (educationEntity.isPresent()) {
            educationRepository.deleteEducationById(id);

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("FeatureService")
                    .setControllerName("EducationController")
                    .setActionUrl("/feature-service/education/delete")
                    .setActionType("DELETE")
                    .setActionResponse("SUCCESS")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            return "Education succesfully deleted";
        }else {
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("FeatureService")
                    .setControllerName("EducationController")
                    .setActionUrl("/feature-service/education/delete")
                    .setActionType("DELETE")
                    .setActionResponse("SUCCESS")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new EntityNotFoundException("Education with provided id not found");
        }
    }

    @Override
    public List<EducationDTO> getAllEducations() {

        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("FeatureService")
                .setControllerName("EducationController")
                .setActionUrl("/feature-service/education/all")
                .setActionType("GET")
                .setActionResponse("SUCCESS")
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        return EducationMapper.mapToProjections(educationRepository.findAll());
    }
}
