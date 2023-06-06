package ba.unsa.etf.pnwt.recommendationservice.controller;

import ba.unsa.etf.pnwt.recommendationservice.dto.JobDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;
import ba.unsa.etf.pnwt.recommendationservice.service.JobServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/recommendation-service/job")
public class JobController {
    private final JobServiceImp jobService;
    @Autowired
    public JobController(JobServiceImp jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobEntity> getJobs(){
        return  jobService.getJobs();
    }

    @PostMapping(path = "/addNewJob")
    public ResponseEntity<JobEntity> addNewJob(@Valid @RequestBody JobEntity jobEntity){
        jobService.addNewJob(jobEntity);
        return ResponseEntity.ok(jobEntity); //vratiti objekat
    }
    @PostMapping(path = "/addNewJobDTO")
    public ResponseEntity<JobDTO> addNewJobDTO(@RequestBody JobDTO jobDTO){
        JobEntity jobEntity = jobService.addNewJobDTO(jobDTO);
        return ResponseEntity.ok(jobDTO); //vratiti objekat
    }
    @DeleteMapping(path="/delete/{jobId}")
    public void deleteRecommendation(@PathVariable("jobId") Long id){
        jobService.deleteRecommendation(id);
    }
}
