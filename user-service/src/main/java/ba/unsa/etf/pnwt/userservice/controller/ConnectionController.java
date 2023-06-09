package ba.unsa.etf.pnwt.userservice.controller;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.dto.MessageDTO;
import ba.unsa.etf.pnwt.userservice.dto.NotificationDTO;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.service.ConnectionService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service/connection")
public class ConnectionController {
    @Autowired protected ConnectionService connectionService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.ALL_USERS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))})}
    )
    @GetMapping("{uuid}/all")
    public ResponseEntity<List<UserDTO>> getAllConnectionsForUserWithUUID
            (@PathVariable("uuid") String uuid) {
        return new ResponseEntity<>(connectionService.findAllConnectionsByUUID(uuid), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.ALL_USERS_FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))})}
    )
    @GetMapping("{uuid}/all/requests")
    public ResponseEntity<List<UserDTO>> getAllConnectionRequestsForUserWithUUID
            (@PathVariable("uuid") String uuid) {
        return new ResponseEntity<>(connectionService.findAllConnectionRequestsByUUID(uuid), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.CONNECTION_SENT,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @PostMapping("{uuidFrom}/start/{uuidTo}")
    public ResponseEntity<NotificationDTO> startNewConnection(
            @PathVariable("uuidFrom") String uuidFrom, @PathVariable("uuidTo") String uuidTo) {
        return new ResponseEntity<>(connectionService.sendConnectionRequest(uuidTo, uuidFrom), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.CONNECTION_ACCEPTED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageDTO.class))})}
    )
    @PutMapping("{uuidFrom}/accept/{uuidTo}")
    public ResponseEntity<MessageDTO> acceptConnection(
            @PathVariable("uuidFrom") String uuidFrom, @PathVariable("uuidTo") String uuidTo) {
        connectionService.acceptConnection(uuidTo, uuidFrom);
        return new ResponseEntity<>(new MessageDTO(ApiResponseMessages.CONNECTION_ACCEPTED), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.CONNECTION_REJECTED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @PutMapping("{uuidFrom}/reject/{uuidTo}")
    public ResponseEntity<MessageDTO> rejectConnection(
            @PathVariable("uuidFrom") String uuidFrom, @PathVariable("uuidTo") String uuidTo) {
        connectionService.rejectConnection(uuidTo, uuidFrom);
        return new ResponseEntity<>(new MessageDTO(ApiResponseMessages.CONNECTION_REJECTED), HttpStatus.OK);
    }
}
