package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import ba.unsa.etf.pnwt.jobsservice.exceptions.NotValidException;
import ba.unsa.etf.pnwt.jobsservice.mapper.JobMapper;
import ba.unsa.etf.pnwt.jobsservice.repository.JobRepository;
import ba.unsa.etf.pnwt.jobsservice.repository.UserRepository;
import ba.unsa.etf.pnwt.proto.Logging;
import ba.unsa.etf.pnwt.proto.LoggingRequest;
import ba.unsa.etf.pnwt.proto.LoggingServiceGrpc;
import jakarta.persistence.EntityNotFoundException;
import net.devh.boot.grpc.client.inject.GrpcClient;
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

    @GrpcClient("logging")
    LoggingServiceGrpc.LoggingServiceBlockingStub loggingServiceBlockingStub;

    @Override
    public List<JobDTO> getAll() {
        List<JobEntity> jobsEntites = jobRepository.findAll();
        List<JobDTO> jobs = JobMapper.mapToProjections(jobsEntites);
        for(int i=0; i<jobs.size(); i++){
            jobs.get(i).setCompanyName(userService.getCompanyName(jobs.get(i).getCompanyId()));
        }

        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("JobService")
                .setControllerName("JobController")
                .setActionUrl("/job-service/job/all")
                .setActionType("GET")
                .setActionResponse("SUCCESS")
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        return jobs;
    }

    @Override
    public JobDTO getById(int id) {
        Optional<JobEntity> job = jobRepository.findById((long) id);
        if (job.isPresent()){
            JobDTO jobDTO = JobMapper.mapToProjection(job.get());
            jobDTO.setCompanyName(userService.getCompanyName(jobDTO.getCompanyId()));

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/get/" + id)
                    .setActionType("GET")
                    .setActionResponse("SUCCESS")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            return jobDTO;
        }
        else {
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/get/" + id)
                    .setActionType("GET")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);
            throw new EntityNotFoundException("Job with provided ID not found");
        }
    }

    @Override
    public JobDTO save(JobDTO job) {

        Optional<JobEntity> jobDb = jobRepository.findById((long) job.getId());
        if(jobDb.isPresent()) throw new NotValidException("Job with provided ID already exists");

        if (Objects.equals(userService.getUserType(job.getCompanyId()), "COMPANY")){
            JobDTO jobDTO = JobMapper.mapToProjection(jobRepository.save(JobMapper.mapToEntity(job)));
            jobDTO.setCompanyName(userService.getCompanyName(jobDTO.getCompanyId()));

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/add")
                    .setActionType("POST")
                    .setActionResponse("SUCCESS")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            return jobDTO;
        }
        else {
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/add")
                    .setActionType("POST")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new NotValidException("User with provided ID is not a Company");
        }
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

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/update")
                    .setActionType("POST")
                    .setActionResponse("SUCCESS")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            return jobUpdated;
        }
            else {
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/update")
                    .setActionType("POST")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

                throw new EntityNotFoundException("Job not found");
        }

    }

    @Override
    public String deleteById(int id) {
        Optional<JobEntity> job = jobRepository.findById((long) id);
        if (job.isPresent()){
            jobRepository.deleteById((long) id);

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/deleteJob/" + id)
                    .setActionType("DELETE")
                    .setActionResponse("SUCCESS")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            return "Job successfully deleted";
        }
        else {
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/deleteJob/" + id)
                    .setActionType("DELETE")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new EntityNotFoundException("Job not found");
        }
    }

    @Override
    public List<JobDTO> getByCompanyId(String id) {
        // Check if user with ID exists
        UserEntity userDb = userRepository.findUserEntityByUid(id);
        if (userDb != null){
            List<JobEntity> jobs = jobRepository.findJobsForCompany(id);

            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/getcompanyjobs/" + id)
                    .setActionType("GET")
                    .setActionResponse("SUCCESS")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            return JobMapper.mapToProjections(jobs, userDb);
        }
        else {
            LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                    .setServiceName("JobService")
                    .setControllerName("JobController")
                    .setActionUrl("/job-service/getcompanyjobs/" + id)
                    .setActionType("GET")
                    .setActionResponse("ERROR")
                    .build();
            loggingServiceBlockingStub.logRequest(loggingRequest);

            throw new EntityNotFoundException("Company not found");
        }
    }

    @Override
    public String getCompanyId(int jobId) {
        Optional<JobEntity> job = jobRepository.findById((long) jobId);
        if (job.isPresent()){
            return job.get().getCompanyId();
        }
        else throw new EntityNotFoundException("Job with provided ID not found");
    }

    @Override
    public JobEntity findJobByTitle(String title) {
        return jobRepository.findJobByTitle(title);
    }

}
