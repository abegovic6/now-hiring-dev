package ba.unsa.etf.pnwt.jobsservice.controller;

import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.JobRecommendationDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.service.JobService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * Example controller
 */
@RestController
@RequestMapping("/job-service/job")
public class JobController {

    @Autowired
    protected JobService jobService;

    @Autowired
    public RestTemplate restTemplate;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all jobs in the system",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JobDTO.class)) })}
    )
    @GetMapping("/all")
    public ResponseEntity<List<JobDTO>> getAll() {
        return new ResponseEntity<>(jobService.getAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the job with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JobDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Job with provided ID not found",
                    content = @Content)})
    @GetMapping("/get")
    public ResponseEntity<JobDTO> get(@RequestParam int id) {
        return new ResponseEntity<>(jobService.getById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new JOB",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JobDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<JobDTO> add(@Valid @RequestBody JobDTO jobDTO){
        ResponseEntity<JobDTO> jobDTOResponseEntity = new ResponseEntity<>(jobService.save(jobDTO), HttpStatus.CREATED);
        if(jobDTOResponseEntity.getStatusCode().is2xxSuccessful()){
            String url = "http://userservice/user-service/notification/" + jobDTO.getCompanyId() + "/created-job";
            restTemplate.postForObject(url, null, String.class);

            String urlJobRec = "http://recommendationservice/recommendation-service/job/addNewJobDTO";
            JobEntity job = jobService.findJobByTitle(jobDTO.getTitle());
            JobRecommendationDTO jobRecommendationDTO = new JobRecommendationDTO(new Long(job.getId()), job.getTitle(), job.getDescription(), null);
            restTemplate.postForObject(urlJobRec, jobRecommendationDTO, JobRecommendationDTO.class);


        }

            return jobDTOResponseEntity;

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated job information",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JobDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Job with provided ID not found",
                    content = @Content)})
    @PostMapping("/update")
    public ResponseEntity<JobDTO> update(@RequestBody JobDTO job){
        return new ResponseEntity<> (jobService.update(job), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the job with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JobDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Job with provided ID not found",
                    content = @Content)})
    @DeleteMapping("/deleteJob")
    public ResponseEntity<String> deleteById(@RequestParam int id){
        return new ResponseEntity<> (jobService.deleteById(id), HttpStatus.OK);
    }
}
