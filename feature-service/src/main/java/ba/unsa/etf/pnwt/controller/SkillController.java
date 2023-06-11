package ba.unsa.etf.pnwt.controller;

import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.dto.SkillDTO;
import ba.unsa.etf.pnwt.dto.UserDTO;
import ba.unsa.etf.pnwt.entity.SkillEntity;
import ba.unsa.etf.pnwt.service.education.EducationService;
import ba.unsa.etf.pnwt.service.skill.SkillService;
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
@RequestMapping("/feature-service/skill")
public class SkillController {


    @Autowired
    SkillService skillService;

    @Operation(description = "Get all skills")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully fetched all skills",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<SkillDTO> skill = skillService.getAllSkills();
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @Operation(description = "Create a new skill")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new skill",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    ResponseEntity<SkillEntity> addNewSkill (
            @Parameter(description = "Skill", required = true)
            @Valid @RequestBody SkillDTO skillDTO) {
        var skill = skillService.createSkill(skillDTO);
        return new ResponseEntity<>(skill,HttpStatus.CREATED);
    }

    @Operation(description = "Get a skill by user ID")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully found skill for provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "Skill for provided user ID not found",
                    content = @Content)})
    @GetMapping(path="/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<SkillDTO>> getSkillForUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable  Integer id) {
        var skill = skillService.findByUserId(id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @Operation(description = "Delete skill")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted skill that was provided"),
            @ApiResponse(responseCode = "404", description = "Invalid information supplied",
                    content = @Content)})
    @DeleteMapping(path="/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> deleteSkill(
            @Parameter(description = "Skill", required = true)
            @PathVariable Integer id) {
        return new ResponseEntity<>(skillService.deleteSkill(id),HttpStatus.OK);
    }
}
