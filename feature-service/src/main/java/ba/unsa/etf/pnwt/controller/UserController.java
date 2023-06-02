package ba.unsa.etf.pnwt.controller;


import ba.unsa.etf.pnwt.dto.ExampleDTO;
import ba.unsa.etf.pnwt.dto.UserDTO;
import ba.unsa.etf.pnwt.entity.UserEntity;
import ba.unsa.etf.pnwt.service.user.UserService;
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
@RequestMapping("/feature-service/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(description = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully fetched all users",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(description = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid information supplied",
                    content = @Content)})
    @PostMapping(path="/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<UserEntity> addNewUser (
            @Parameter(description = "User", required = true)
            @Valid @RequestBody UserDTO userDTO) {
        var user = userService.crateUser(userDTO);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }


    @Operation(description = "Get a user by ID")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the user with provided ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "User with provided ID not found",
                    content = @Content)})
    @GetMapping(path="/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<UserDTO> getUserById(
            @Parameter(description = "User ID", required = true)
            @PathVariable  Integer id) {
        var user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(description = "Get a user by ID")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the user with provided UUID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "User with provided UUID not found",
                    content = @Content)})
    @GetMapping(path="/uuid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<UserDTO> getUserByUuid(
            @Parameter(description = "User ID", required = true)
            @PathVariable  String id) {
        var user = userService.getUserByUuid(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(description = "Get a user by Email")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the user with provided Email",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "User with provided Email not found",
                    content = @Content)})
    @GetMapping(path="/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<UserDTO> getUserByEmail(
            @Parameter(description = "User Email", required = true)
            @PathVariable  String email) {
        var user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @Operation(description = "Delete user")
    @ApiResponses ( value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the user that was provided"),
            @ApiResponse(responseCode = "404", description = "Invalid information supplied",
                    content = @Content)})
    @DeleteMapping(path="/delete/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> deleteUser(
            @Parameter(description = "User", required = true)
            @PathVariable String uuid) {
        return new ResponseEntity<>(userService.deleteUser(uuid),HttpStatus.OK);
    }
}
