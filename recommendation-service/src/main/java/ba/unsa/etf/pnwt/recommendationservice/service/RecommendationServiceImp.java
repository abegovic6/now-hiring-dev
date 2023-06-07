package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.proto.LoggingRequest;
import ba.unsa.etf.pnwt.proto.LoggingServiceGrpc;
import ba.unsa.etf.pnwt.recommendationservice.dto.RecommendationHelperDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.RecommendationEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.exceptions.ApiRequestException;
import ba.unsa.etf.pnwt.recommendationservice.repository.RecommendationRepository;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImp implements RecommendationService{

    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationServiceImp(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @GrpcClient("logging")
    LoggingServiceGrpc.LoggingServiceBlockingStub loggingServiceBlockingStub;


    @Override
    public List<RecommendationEntity> getRecommendation() {

        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("RecommendationService")
                .setControllerName("RecommendationController")
                .setActionUrl("/recommendation-service/recommendation/all")
                .setActionType("GET")
                .setActionResponse("SUCCESS")
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        return recommendationRepository.findAll();
    }

    @Override
    @Transactional
    public void addNewRecommendation(RecommendationEntity recommendation) {
        Optional<UserEntity> owner = recommendationRepository.findUserByEmail(recommendation.getUserEntity().getEmail());
        System.out.println(owner);
        Optional<JobEntity> job = recommendationRepository.findJobById(recommendation.getJobEntity().getId());
        Optional<UserEntity> recommendedUser = recommendationRepository.findUserByEmail(recommendation.getRecommendedUser().getEmail());
        if(!job.isPresent()){
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("RecommendationService")
                    .setControllerName("RecommendationController")
                    .setActionUrl("/recommendation-service/recommendation/addNewRecommendation")
                    .setActionType("POST")
                    .setActionResponse("ERROR")
                    .setUserUuid(recommendation.getUserEntity().getUuid())
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new ApiRequestException("Job with given id " + recommendation.getJobEntity().getId()+
                    "doesn't exist"); //exception handeling
        }
        else if(!owner.isPresent()){
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("RecommendationService")
                    .setControllerName("RecommendationController")
                    .setActionUrl("/recommendation-service/recommendation/addNewRecommendation")
                    .setActionType("POST")
                    .setActionResponse("ERROR")
                    .setUserUuid(recommendation.getUserEntity().getUuid())
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new ApiRequestException("User with given id " + recommendation.getUserEntity().getId()+
            " and email " + recommendation.getUserEntity().getEmail() + " doesn't exist");
        }
        else if(!recommendedUser.isPresent()){
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("RecommendationService")
                    .setControllerName("RecommendationController")
                    .setActionUrl("/recommendation-service/recommendation/addNewRecommendation")
                    .setActionType("POST")
                    .setActionResponse("ERROR")
                    .setUserUuid(recommendation.getUserEntity().getUuid())
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new ApiRequestException("User with given id " + recommendation.getRecommendedUser().getId()+
                    " and email " + recommendation.getUserEntity().getEmail() + " doesn't exist");
        }

        recommendation.getUserEntity().setId(owner.get().getId());
        recommendation.getJobEntity().setId(job.get().getId());
        recommendation.getRecommendedUser().setId(recommendedUser.get().getId());

        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("RecommendationService")
                .setControllerName("RecommendationController")
                .setActionUrl("/recommendation-service/recommendation/addNewRecommendation")
                .setActionType("POST")
                .setActionResponse("SUCCESS")
                .setUserUuid(recommendation.getUserEntity().getUuid())
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        recommendationRepository.save(recommendation);
    }

    @Override
    public void deleteRecommendation(Long id) {
        boolean exists = recommendationRepository.existsById(id);
        if(!exists){

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("RecommendationService")
                    .setControllerName("RecommendationController")
                    .setActionUrl("/recommendation-service/recommendation/delete/" + id)
                    .setActionType("DELETE")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw  new ApiRequestException("Recommendation with id "+ id +" doesn't exist");
        }

        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("RecommendationService")
                .setControllerName("RecommendationController")
                .setActionUrl("/recommendation-service/recommendation/delete/" + id)
                .setActionType("DELETE")
                .setActionResponse("SUCCES")
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        recommendationRepository.deleteById(id);
    }

    @Override
    public RecommendationEntity addNewRecommendation(RecommendationHelperDTO recommendation) {
        Optional<UserEntity> userById = recommendationRepository.findUserById(recommendation.getUserId());
        Optional<JobEntity> jobById = recommendationRepository.findJobById(recommendation.getJobId());
        Optional<UserEntity> recommendedUserById = recommendationRepository.findUserById(recommendation.getRecommendedUser());
        if(!userById.isPresent()){

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("RecommendationService")
                    .setControllerName("RecommendationController")
                    .setActionUrl("/recommendation-service/recommendation/addRecomendationDTO")
                    .setActionType("POST")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new ApiRequestException("User with given id "+ recommendation.getUserId()+" doesn't exist!");
        }
        else if(!jobById.isPresent()){

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("RecommendationService")
                    .setControllerName("RecommendationController")
                    .setActionUrl("/recommendation-service/recommendation/addRecomendationDTO")
                    .setActionType("POST")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new ApiRequestException("Job with given id "+recommendation.getJobId()+" doesn't exist!");
        }
        else if(!recommendedUserById.isPresent()){

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("RecommendationService")
                    .setControllerName("RecommendationController")
                    .setActionUrl("/recommendation-service/recommendation/addRecomendationDTO")
                    .setActionType("POST")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new ApiRequestException("User with given id doesn't exist!");
        }

            RecommendationEntity savedRecommendation = new RecommendationEntity(recommendation.getDescription(), userById.get(), jobById.get(), recommendedUserById.get());
            recommendationRepository.save(savedRecommendation);

        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("RecommendationService")
                .setControllerName("RecommendationController")
                .setActionUrl("/recommendation-service/recommendation/addRecomendationDTO")
                .setActionType("POST")
                .setActionResponse("SUCCESS")
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        return savedRecommendation;

    }
}
