package ba.unsa.etf.pnwt.jobsservice.controller;

import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.UserDTO;
import ba.unsa.etf.pnwt.jobsservice.service.JobService;
import ba.unsa.etf.pnwt.jobsservice.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Example controller
 */
@RestController
@RequestMapping("/job-service/user")
public class UserController {

    @Autowired
    protected UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all users in the system",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) })}
    )
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the user with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "User with provided ID not found",
                    content = @Content)})
    @GetMapping("/get")
    public ResponseEntity<UserDTO> get(@RequestParam String uid){
        UserDTO userDTO = userService.getByUid(uid);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<UserDTO> add(@Valid @RequestBody UserDTO user){
        UserDTO userDTO = userService.save(user);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user information",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User with provided ID not found",
                    content = @Content)})
    @PostMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user){
        return new ResponseEntity<> (userService.update(user), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the user with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "User with provided ID not found",
                    content = @Content)})
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteById(@RequestParam String id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }
}
