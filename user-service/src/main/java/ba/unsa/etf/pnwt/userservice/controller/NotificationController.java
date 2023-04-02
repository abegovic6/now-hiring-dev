package ba.unsa.etf.pnwt.userservice.controller;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
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

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired protected NotificationService notificationService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.NOTIFICATION_CREATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @PostMapping("{companyUuid}/created-job")
    public ResponseEntity<String> createCompanyCreatedAJobNotification(
            @PathVariable("companyUuid") String companyUuid
    ) {
        notificationService.createCompanyCreatedAJobNotification(companyUuid);
        return new ResponseEntity<>(ApiResponseMessages.NOTIFICATION_CREATED, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.NOTIFICATION_CREATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @PostMapping("{userUuid}/user-applied-for-job/{companyUuid}")
    public ResponseEntity<String> createUserAppliedForJobApplication(
            @PathVariable("userUuid") String userUuid, @PathVariable("companyUuid") String companyUuid
    ) {
        notificationService.createUserAppliedForJobApplication(userUuid, companyUuid);
        return new ResponseEntity<>(ApiResponseMessages.NOTIFICATION_CREATED, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiResponseMessages.NOTIFICATION_CREATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))})}
    )
    @PostMapping("{uuidReviewer}/review/{userUuid}")
    public ResponseEntity<String> createCompanyCreatedAJobNotification(
            @PathVariable("uuidReviewer") String uuidReviewer,
            @PathVariable("userUuid") String userUuid
    ) {
        notificationService.createReviewerWroteAReviewForUser(uuidReviewer, userUuid);
        return new ResponseEntity<>(ApiResponseMessages.NOTIFICATION_CREATED, HttpStatus.OK);
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
