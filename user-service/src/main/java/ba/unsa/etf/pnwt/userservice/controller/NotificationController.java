package ba.unsa.etf.pnwt.userservice.controller;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.dto.NotificationDTO;
import ba.unsa.etf.pnwt.userservice.service.NotificationService;
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
@RequestMapping("/user-service/notification")
public class NotificationController {

    @Autowired protected NotificationService notificationService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.NOTIFICATION_CREATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @PostMapping("{companyUuid}/created-job")
    public ResponseEntity<List<NotificationDTO>> createCompanyCreatedAJobNotification(
            @PathVariable("companyUuid") String companyUuid
    ) {
        return new ResponseEntity<>(notificationService.createCompanyCreatedAJobNotification(companyUuid), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.NOTIFICATION_CREATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @PostMapping("{userUuid}/user-applied-for-job/{companyUuid}")
    public ResponseEntity<NotificationDTO> createUserAppliedForJobApplication(
            @PathVariable("userUuid") String userUuid, @PathVariable("companyUuid") String companyUuid
    ) {
        return new ResponseEntity<>(
                notificationService.createUserAppliedForJobApplication(userUuid, companyUuid), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.NOTIFICATION_CREATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @PostMapping("{uuidReviewer}/review/{userUuid}")
    public ResponseEntity<NotificationDTO> createUserWroteAReviewNotification(
            @PathVariable("uuidReviewer") String uuidReviewer,
            @PathVariable("userUuid") String userUuid
    ) {
        return new ResponseEntity<>(
                notificationService.createReviewerWroteAReviewForUser(uuidReviewer, userUuid), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.MAIL_SENT,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @GetMapping("test")
    public ResponseEntity<String> sendTestMail() {
        notificationService.sendTestMail();
        return new ResponseEntity<>(ApiResponseMessages.MAIL_SENT, HttpStatus.OK);
    }
}
