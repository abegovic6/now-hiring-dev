package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.mapper.JobMapper;
import ba.unsa.etf.pnwt.jobsservice.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobDTO> getAll() {
        return JobMapper.mapToProjections(jobRepository.findAll());
    }

    @Override
    public JobDTO getById(int id) {
        Optional<JobEntity> job = jobRepository.findById((long) id);
        if (job.isPresent())
            return JobMapper.mapToProjection(job.get());
        else throw new EntityNotFoundException("Job with provided ID not found");
    }

    @Override
    public JobDTO save(JobDTO job) {
        return JobMapper.mapToProjection(jobRepository.save(JobMapper.mapToEntity(job)));
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
            return JobMapper.mapToProjection(jobRepository.save(JobMapper.mapToEntity(job)));
        }
            else throw new EntityNotFoundException("Job not found");

    }

    @Override
    public void delete(JobDTO job) {
        jobRepository.delete(JobMapper.mapToEntity(job));
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

}
