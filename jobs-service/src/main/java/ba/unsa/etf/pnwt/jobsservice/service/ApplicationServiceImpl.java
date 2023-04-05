package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.ApplicationDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.ApplicationEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.Job2ApplicationEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import ba.unsa.etf.pnwt.jobsservice.exceptions.NotValidException;
import ba.unsa.etf.pnwt.jobsservice.mapper.ApplicationMapper;
import ba.unsa.etf.pnwt.jobsservice.mapper.Job2ApplicationMapper;
import ba.unsa.etf.pnwt.jobsservice.mapper.JobMapper;
import ba.unsa.etf.pnwt.jobsservice.repository.ApplicationRepository;
import ba.unsa.etf.pnwt.jobsservice.repository.Job2ApplicationRepository;
import ba.unsa.etf.pnwt.jobsservice.repository.JobRepository;
import ba.unsa.etf.pnwt.jobsservice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Job2ApplicationRepository job2ApplicationRepository;

    @Autowired
    private UserService userService;


    @Override
    public List<ApplicationDTO> getAll() {
        List<JobEntity> jobs = jobRepository.findAll();
        List<Integer> jobIdS = jobs.stream().map(JobEntity::getId).toList();
        List<ApplicationDTO> apps = new ArrayList<>();

        for(int i=0; i<jobIdS.size(); i++){
            List<ApplicationDTO> appsforjob = getApplicationsForJobId(jobIdS.get(i));
            apps.addAll(appsforjob);
        }
        return apps;
    }

    @Override
    public ApplicationDTO getById(int id) {
        Optional<ApplicationEntity> application = applicationRepository.findById((long) id);
        if (application.isPresent()){
            Integer jobId = job2ApplicationRepository.findJob2ApplicationEntityByApplicationId(id).getJobId();
            UserEntity userDb = userRepository.findUserEntityByUid(application.get().getUserUid());
            ApplicationDTO applicationDTO = ApplicationMapper.mapToProjection(application.get());
            applicationDTO.setFirstName(userDb.getFirstName());
            applicationDTO.setLastName(userDb.getLastName());
            applicationDTO.setLocation(userDb.getLocation());
            applicationDTO.setDescription(userDb.getDescription());
            applicationDTO.setJobId(jobId);
            return applicationDTO;
        }
        else throw new EntityNotFoundException("Application with provided ID not found");
    }

    @Override
    public ApplicationDTO save(ApplicationDTO app) {
        return ApplicationMapper.mapToProjection(applicationRepository.save(ApplicationMapper.mapToEntity(app, null)));
    }

    @Override
    public ApplicationDTO update(ApplicationDTO app) {
        Optional<ApplicationEntity> application = applicationRepository.findById((long) app.getId());
        if(application.isPresent()){
            return ApplicationMapper.mapToProjection(applicationRepository.save(ApplicationMapper.mapToEntity(app, null)));
        }
        else throw new EntityNotFoundException("Application with provided ID not found");
    }


    @Override
    public String deleteById(int id) {
        Optional<ApplicationEntity> application = applicationRepository.findById((long) id);
        if(application.isPresent()){
            applicationRepository.deleteById((long) id);
            return "Application successfully deleted";
        }
        else throw new EntityNotFoundException("Application with provided ID not found");
    }

    @Override
    public ApplicationDTO createApplication(ApplicationDTO app) {
        if (app.getJobId() == null){
            throw new NotValidException("Job ID not provided");
        }
        if (app.getUserId() == null){
            throw new NotValidException("User ID not provided");
        }

        UserEntity userDb = userRepository.findUserEntityByUid(app.getUserId());
        Optional<JobEntity> jobDb = jobRepository.findById((long) app.getJobId());

        if (userDb == null){
            throw new EntityNotFoundException("User with provided ID not found");
        }

        if (Objects.equals(userService.getUserType(app.getUserId()), "COMPANY")){
            throw new NotValidException("User with provided ID is a Company and can't apply for a job");
        }

        if (!jobDb.isPresent()){
            throw new EntityNotFoundException("Job with provided ID not found");
        }

        if (jobDb.get().getValidTo().isBefore(LocalDate.now())){
            throw new NotValidException("This job has expired");
        }

        ApplicationEntity applicationEntity = ApplicationMapper.mapToEntity(app, null);

        ApplicationEntity applicationEntityDb = applicationRepository.save(applicationEntity);

        Job2ApplicationEntity job2ApplicationEntity = new Job2ApplicationEntity();
        job2ApplicationEntity.setJobId(jobDb.get().getId());
        job2ApplicationEntity.setApplicationId(applicationEntityDb.getId());

        job2ApplicationRepository.save(job2ApplicationEntity);

        ApplicationDTO applicationDTO = ApplicationMapper.mapToProjection(applicationEntityDb, userDb);
        applicationDTO.setJobId(app.getJobId());
        return applicationDTO;


    }

    @Override
    public List<ApplicationDTO> getApplicationsForUserId(String userId) {
        UserEntity userDb = userRepository.findUserEntityByUid(userId);
        if (userDb == null){
            throw new EntityNotFoundException("User with provided ID not found");
        }

        if (Objects.equals(userService.getUserType(userId), "COMPANY")){
            throw new NotValidException("User with provided ID is a Company and can't apply for jobs");
        }
        List<ApplicationEntity> userApplications = applicationRepository.findApplicationEntityByUserUid(userId);
        List<Job2ApplicationEntity> job2Applications = job2ApplicationRepository.findAll();

        List<ApplicationDTO> userApplicationDTOs = ApplicationMapper.mapToProjections(userApplications, userDb);

        List<Integer> applicationIds = userApplications.stream().map(ApplicationEntity::getId).toList();

        List<Integer> jobIds = Job2ApplicationEntity.getJobIdsForAppIds(job2Applications, applicationIds);

        for(int i=0; i < userApplicationDTOs.size(); i++){
            userApplicationDTOs.get(i).setJobId(jobIds.get(i));
        }

        return userApplicationDTOs;

    }

    @Override
    public List<ApplicationDTO> getApplicationsForJobId(int jobId) {

        Optional<JobEntity> jobDb = jobRepository.findById((long) jobId);
        if (!jobDb.isPresent()){
            throw new EntityNotFoundException("Job with provided ID not found");
        }

        List<Job2ApplicationEntity> j2aList = job2ApplicationRepository.findAll();
        j2aList = j2aList.stream().filter(j2a -> j2a.getJobId() == jobId).collect(Collectors.toList());
        List<Integer> appIds = j2aList.stream().map(Job2ApplicationEntity::getApplicationId).toList();
        List<ApplicationEntity> apps = applicationRepository.findAll();
        apps = apps.stream().filter(application -> appIds.contains(application.getId())).collect(Collectors.toList());

        List<ApplicationDTO> applicationDTOS = ApplicationMapper.mapToProjections(apps);

        for(int i=0; i<applicationDTOS.size(); i++){
            applicationDTOS.get(i).setJobId(jobId);
            UserEntity userDb = userRepository.findUserEntityByUid(applicationDTOS.get(i).getUserId());
            applicationDTOS.get(i).setFirstName(userDb.getFirstName());
            applicationDTOS.get(i).setLastName(userDb.getLastName());
            applicationDTOS.get(i).setLocation(userDb.getLocation());
            applicationDTOS.get(i).setDescription(userDb.getDescription());
        }

        return applicationDTOS;
    }
}
