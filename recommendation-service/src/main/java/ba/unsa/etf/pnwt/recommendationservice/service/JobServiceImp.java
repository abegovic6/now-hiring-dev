package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.dto.JobDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.exceptions.ApiRequestException;
import ba.unsa.etf.pnwt.recommendationservice.repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImp implements JobService{
    private final JobRepository jobRepository;
    @Autowired
    public JobServiceImp(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobEntity> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    @Transactional
    public void addNewJob(JobEntity job) {
        Optional<UserEntity> owner = jobRepository.findUserByEmail(job.getUserEntity().getEmail());
        System.out.println(owner);
        if(!owner.isPresent()){
            throw new ApiRequestException("User with given id " + job.getUserEntity().getId()+
                    " and email " + job.getUserEntity().getEmail() + "doesn't exist");
        }
        job.getUserEntity().setId(owner.get().getId());
        jobRepository.save(job);
    }

    @Override
    public void deleteRecommendation(Long id) {
        boolean exists = jobRepository.existsById(id);
        if(!exists){
            throw  new ApiRequestException("Jon with id "+ id +" doesn't exist");
        }
        jobRepository.deleteById(id);
    }

    @Override
    public JobEntity addNewJobDTO(JobDTO jobDTO) {

        //Optional<UserEntity> owner = jobRepository.findUserById(jobDTO.getUserUuid());
        JobEntity newJob = new JobEntity(jobDTO.getName(), jobDTO.getDescription(), null);
        jobRepository.save(newJob);
        return newJob;
    }
}
