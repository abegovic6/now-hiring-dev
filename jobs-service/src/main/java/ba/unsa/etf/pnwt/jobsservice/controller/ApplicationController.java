package ba.unsa.etf.pnwt.jobsservice.controller;

import ba.unsa.etf.pnwt.jobsservice.dto.ApplicationDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.ApplicationEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.service.ApplicationService;
import ba.unsa.etf.pnwt.jobsservice.service.JobService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

/**
 * Example controller
 */
@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    @Autowired
    protected ApplicationService applicationService;

    @Autowired
    protected JobService jobService;

    @Autowired
    public RestTemplate restTemplate;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all applications in the system",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationDTO.class)) })}
    )
    @GetMapping("/all")
    public ResponseEntity<List<ApplicationDTO>> getAll() {
        return new ResponseEntity<>(applicationService.getAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the application with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Application with provided ID not found",
                    content = @Content)})
    @GetMapping("/get")
    public ResponseEntity<ApplicationDTO> get(@RequestParam int id) {
        return new ResponseEntity<>(applicationService.getById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all applications for User with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "User with provided ID not found",
                    content = @Content)})
    @GetMapping("/getAppsForUser")
    public ResponseEntity<List<ApplicationDTO>> get(@RequestParam String id) {
        return new ResponseEntity<>(applicationService.getApplicationsForUserId(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all applications for Job with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Job with provided ID not found",
                    content = @Content)})
    @GetMapping("/getAppsForJob")
    public ResponseEntity<List<ApplicationDTO>> getForJob(@RequestParam int id) {
        return new ResponseEntity<>(applicationService.getApplicationsForJobId(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created job application for user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApplicationDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User or Job with provided ID not found",
                    content = @Content)})
    @PostMapping("/create")
    public ResponseEntity<ApplicationDTO> create(@Valid @RequestBody ApplicationDTO app){
        ResponseEntity<ApplicationDTO> applicationDTOResponseEntity = new ResponseEntity<>(applicationService.createApplication(app), HttpStatus.CREATED);
        if(applicationDTOResponseEntity.getStatusCode().is2xxSuccessful()){
            String companyUuid = jobService.getCompanyId(app.getJobId());
            String userUuid = app.getUserId();
            String url = "http://userservice/api/notification/" + userUuid + "/user-applied-for-job/" + companyUuid;
            restTemplate.postForObject(url, null, String.class);
            return applicationDTOResponseEntity;
        }
        else{
            return applicationDTOResponseEntity;
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the application with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JobDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Application with provided ID not found",
                    content = @Content)})
    @DeleteMapping("/deleteApplication")
    public ResponseEntity<String> deleteById(@RequestParam int id){
        return new ResponseEntity<>(applicationService.deleteById(id), HttpStatus.OK);
    }
}
