package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import ba.unsa.etf.pnwt.jobsservice.exceptions.NotValidException;
import ba.unsa.etf.pnwt.jobsservice.mapper.JobMapper;
import ba.unsa.etf.pnwt.jobsservice.repository.JobRepository;
import ba.unsa.etf.pnwt.jobsservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<JobDTO> getAll() {
        List<JobEntity> jobsEntites = jobRepository.findAll();
        List<JobDTO> jobs = JobMapper.mapToProjections(jobsEntites);
        for(int i=0; i<jobs.size(); i++){
            jobs.get(i).setCompanyName(userService.getCompanyName(jobs.get(i).getCompanyId()));
        }
        return jobs;
    }

    @Override
    public JobDTO getById(int id) {
        Optional<JobEntity> job = jobRepository.findById((long) id);
        if (job.isPresent()){
            JobDTO jobDTO = JobMapper.mapToProjection(job.get());
            jobDTO.setCompanyName(userService.getCompanyName(jobDTO.getCompanyId()));
            return jobDTO;
        }
        else throw new EntityNotFoundException("Job with provided ID not found");
    }

    @Override
    public JobDTO save(JobDTO job) {

        Optional<JobEntity> jobDb = jobRepository.findById((long) job.getId());
        if(jobDb.isPresent()) throw new NotValidException("Job with provided ID already exists");

        if (Objects.equals(userService.getUserType(job.getCompanyId()), "COMPANY")){
            JobDTO jobDTO = JobMapper.mapToProjection(jobRepository.save(JobMapper.mapToEntity(job)));
            jobDTO.setCompanyName(userService.getCompanyName(jobDTO.getCompanyId()));
            return jobDTO;
        }
        else throw new NotValidException("User with provided ID is not a Company");
    }

    @Override
    public JobDTO update(JobDTO job) {
        Optional<JobEntity> jobDb = jobRepository.findById((long) job.getId());
        if(jobDb.isPresent()){
            if(job.getTitle() == null) job.setTitle(jobDb.get().getTitle());
            if(job.getLocation() == null) job.setLocation(jobDb.get().getLocation());
            if(job.getType() == null) job.setType(jobDb.get().getType());
            if(job.getValidTo() == null) job.setValidTo(jobDb.get().getValidTo());
            if(job.getDescription() == null) job.setDescription(jobDb.get().getDescription());

            job.setCompanyId(jobDb.get().getCompanyId());
            // NOTE: Company id or name can't be modified

            JobDTO jobUpdated = JobMapper.mapToProjection(jobRepository.save(JobMapper.mapToEntity(job)));
            jobUpdated.setCompanyName(userService.getCompanyName(jobDb.get().getCompanyId()));
            return jobUpdated;
        }
            else throw new EntityNotFoundException("Job not found");

    }

    @Override
    public String deleteById(int id) {
        Optional<JobEntity> job = jobRepository.findById((long) id);
        if (job.isPresent()){
            jobRepository.deleteById((long) id);
            return "Job successfully deleted";
        }
        else throw new EntityNotFoundException("Job not found");
    }

    @Override
    public List<JobDTO> getByCompanyId(String id) {
        // Check if user with ID exists
        UserEntity userDb = userRepository.findUserEntityByUid(id);
        if (userDb != null){
            List<JobEntity> jobs = jobRepository.findJobsForCompany(id);
            return JobMapper.mapToProjections(jobs, userDb);
        }
        else throw new EntityNotFoundException("Company not found");
    }

}
