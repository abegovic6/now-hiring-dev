package ba.unsa.etf.pnwt.controller;

import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.dto.UserDTO;
import ba.unsa.etf.pnwt.entity.EducationEntity;
import ba.unsa.etf.pnwt.repository.EducationRepository;
import ba.unsa.etf.pnwt.service.education.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/feature-service/education")
public class EducationController {

    @Autowired
    EducationService educationService;

    @Operation(description = "Get all education")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully fetched all educations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<EducationDTO>> getAllEducations() {
        List<EducationDTO> educations = educationService.getAllEducations();
        return new ResponseEntity<>(educations, HttpStatus.OK);
    }

    @Operation(description = "Create a new education")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new education",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    ResponseEntity<EducationEntity> addNewEducation (
            @Parameter(description = "Education", required = true)
            @Valid @RequestBody EducationDTO educationDTO) {
        var user = educationService.createEducation(educationDTO);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @Operation(description = "Get a education by user ID")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully found education for provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "Education for provided user ID not found",
                    content = @Content)})
    @GetMapping(path="/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<EducationDTO>> getEducationForUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable  Integer id) {
        var user = educationService.findEducationByUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(description = "Delete education")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted education that was provided"),
            @ApiResponse(responseCode = "404", description = "Invalid information supplied",
                    content = @Content)})
    @DeleteMapping(path="/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> deleteEducation(
            @Parameter(description = "Education", required = true)
            @PathVariable Integer id) {
        return new ResponseEntity<>(educationService.deleteEducation(id),HttpStatus.OK);
    }
}
