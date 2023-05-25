package ba.unsa.etf.pnwt.controller;

import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.dto.ExperienceDTO;
import ba.unsa.etf.pnwt.dto.UserDTO;
import ba.unsa.etf.pnwt.service.education.EducationService;
import ba.unsa.etf.pnwt.service.experience.ExperienceService;
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
@RequestMapping("/feature-service/experience")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @Operation(description = "Get all experiences")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully fetched all experiences",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<ExperienceDTO>> getAllExperience() {
        List<ExperienceDTO> experience = experienceService.getAllExperience();
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }

    @Operation(description = "Create a new experience")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new experience",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    ResponseEntity<ExperienceDTO> addNewExperience (
            @Parameter(description = "Education", required = true)
            @Valid @RequestBody ExperienceDTO experienceDTO) {
        var user = experienceService.createExperience(experienceDTO);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @Operation(description = "Get a experience by user ID")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully found experience for provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "Experience for provided user ID not found",
                    content = @Content)})
    @GetMapping(path="/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<ExperienceDTO>> getExperienceForUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable  Integer id) {
        var experience = experienceService.findExperienceByUser(id);
        return new ResponseEntity<>(experience, HttpStatus.OK);
    }

    @Operation(description = "Delete experience")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted experience that was provided"),
            @ApiResponse(responseCode = "404", description = "Invalid information supplied",
                    content = @Content)})
    @DeleteMapping(path="/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> deleteExperience(
            @Parameter(description = "Experience", required = true)
            @PathVariable Integer id) {
        return new ResponseEntity<>(experienceService.deleteExperience(id),HttpStatus.OK);
    }
}
