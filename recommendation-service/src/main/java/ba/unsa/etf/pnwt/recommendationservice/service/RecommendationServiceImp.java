package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.dto.RecommendationHelperDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.RecommendationEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.exceptions.ApiRequestException;
import ba.unsa.etf.pnwt.recommendationservice.repository.RecommendationRepository;
import jakarta.transaction.Transactional;
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

    @Override
    public List<RecommendationEntity> getRecommendation() {
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
            throw new ApiRequestException("Job with given id " + recommendation.getJobEntity().getId()+
                    "doesn't exist"); //exception handeling
        }
        else if(!owner.isPresent()){
            throw new ApiRequestException("User with given id " + recommendation.getUserEntity().getId()+
            " and email " + recommendation.getUserEntity().getEmail() + " doesn't exist");
        }
        else if(!recommendedUser.isPresent()){
            throw new ApiRequestException("User with given id " + recommendation.getRecommendedUser().getId()+
                    " and email " + recommendation.getUserEntity().getEmail() + " doesn't exist");
        }

        recommendation.setUserEntity(owner.get());
        recommendation.setJobEntity(job.get());
        recommendation.setRecommendedUser(recommendedUser.get());
        recommendationRepository.save(recommendation);
    }

    @Override
    public void deleteRecommendation(Long id) {
        boolean exists = recommendationRepository.existsById(id);
        if(!exists){
            throw  new ApiRequestException("Recommendation with id "+ id +" doesn't exist");
        }
        recommendationRepository.deleteById(id);
    }

    @Override
    public RecommendationEntity addNewRecommendation(RecommendationHelperDTO recommendation) {
        Optional<UserEntity> userById = recommendationRepository.findUserById(recommendation.getUserId());
        Optional<JobEntity> jobById = recommendationRepository.findJobById(recommendation.getJobId());
        Optional<UserEntity> recommendedUserById = recommendationRepository.findUserById(recommendation.getRecommendedUser());
        if(!userById.isPresent()){
            throw new ApiRequestException("User with given id "+ recommendation.getUserId()+" doesn't exist!");
        }
        else if(!jobById.isPresent()){
            throw new ApiRequestException("Job with given id "+recommendation.getJobId()+" doesn't exist!");
        }
        else if(!recommendedUserById.isPresent()){
            throw new ApiRequestException("User with given id doesn't exist!");
        }


            RecommendationEntity savedRecommendation = new RecommendationEntity(recommendation.getDescription(), userById.get(), jobById.get(), recommendedUserById.get());
            recommendationRepository.save(savedRecommendation);

        return savedRecommendation;

    }
}
