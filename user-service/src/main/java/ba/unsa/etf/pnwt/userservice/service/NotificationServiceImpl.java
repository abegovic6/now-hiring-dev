package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.proto.LoggingRequest;
import ba.unsa.etf.pnwt.proto.LoggingServiceGrpc;
import ba.unsa.etf.pnwt.userservice.constants.ConnectionStatus;
import ba.unsa.etf.pnwt.userservice.constants.NotificationType;
import ba.unsa.etf.pnwt.userservice.constants.Role;
import ba.unsa.etf.pnwt.userservice.dto.NotificationDTO;
import ba.unsa.etf.pnwt.userservice.entity.ConnectionEntity;
import ba.unsa.etf.pnwt.userservice.entity.NotificationEntity;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import ba.unsa.etf.pnwt.userservice.mapper.NotificationMapper;
import ba.unsa.etf.pnwt.userservice.repository.ConnectionRepository;
import ba.unsa.etf.pnwt.userservice.repository.NotificationRepository;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired protected EmailService emailService;
    @Autowired protected UserService userService;
    @Autowired protected NotificationRepository notificationRepository;
    @Autowired protected ConnectionRepository connectionRepository;

    @GrpcClient("logging")
    LoggingServiceGrpc.LoggingServiceBlockingStub loggingServiceBlockingStub;

    @Override
    public NotificationDTO createConnectionNotification(String uuidFrom, String uuidTo) {
        UserEntity userFor = userService.getUserEntityByUUID(uuidTo);
        UserEntity userFrom = userService.getUserEntityByUUID(uuidFrom);

        sendMail(userFor, userFrom, NotificationType.CONNECTION);
        return NotificationMapper.mapToProjection(saveNotification(userFor, userFrom, NotificationType.CONNECTION));
    }

    private List<UserEntity> findAllConnectionEntities(UserEntity user) {
        List<ConnectionEntity> allConnections
                = connectionRepository.getConnectionEntitiesByConnectionStatusAndUserFromOrUserTo(ConnectionStatus.ACCEPTED, user, user);

        List<UserEntity> connectedUsers = new ArrayList<>();
        for (var connection: allConnections) {
            if (!Objects.equals(connection.getUserFrom().getUuid(), user.getUuid())) {
                connectedUsers.add(connection.getUserFrom());
            }
            if (!Objects.equals(connection.getUserTo().getUuid(), user.getUuid())) {
                connectedUsers.add(connection.getUserTo());
            }
        }
        return connectedUsers;
    }

    @Override
    public List<NotificationDTO> createCompanyCreatedAJobNotification(String uuidCompany) {
        UserEntity company = userService.getUserEntityByUUID(uuidCompany);

        List<UserEntity> userEntitiesFor = findAllConnectionEntities(company);
        List<NotificationEntity> notificationEntities = new ArrayList<>();
        for (UserEntity userFor : userEntitiesFor) {
            sendMail(company, userFor, NotificationType.JOB_CREATED);
            notificationEntities.add(saveNotification(company, userFor, NotificationType.JOB_CREATED));
        }

        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("UserService")
                .setControllerName("NotificationController")
                .setActionUrl("/user-service/notification/" + uuidCompany + "created-job")
                .setActionType("GET")
                .setActionResponse("SUCCESS")
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        return NotificationMapper.mapToProjections(notificationEntities);
    }

    @Override
    public NotificationDTO createUserAppliedForJobApplication(String uuidUser, String uuidCompany) {
        UserEntity user = userService.getUserEntityByUUID(uuidUser);
        UserEntity company = userService.getUserEntityByUUID(uuidCompany);

        sendMail(company, user, NotificationType.JOB_APPLICATION);

        LoggingRequest loggingRequest = LoggingRequest.newBuilder()
                .setServiceName("UserService")
                .setControllerName("NotificationController")
                .setActionUrl("/user-service/notification/" + uuidUser + "/user-applied-for-job/" + uuidCompany)
                .setActionType("POST")
                .setActionResponse("SUCCESS")
                .build();
        loggingServiceBlockingStub.logRequest(loggingRequest);

        return NotificationMapper.mapToProjection(saveNotification(company, user, NotificationType.JOB_APPLICATION));
    }

    @Override
    public NotificationDTO createReviewerWroteAReviewForUser(String uuidReviewer, String uuidForUser) {
        UserEntity userFor = userService.getUserEntityByUUID(uuidForUser);
        UserEntity userFrom = userService.getUserEntityByUUID(uuidReviewer);

        sendMail(userFor, userFrom, NotificationType.REVIEW);
        return NotificationMapper.mapToProjection(saveNotification(userFor, userFrom, NotificationType.REVIEW));
    }

    @Override
    public NotificationDTO createReviewerWroteARecommendationForUser(String uuidReviewer, String uuidForUser) {
        UserEntity userFor = userService.getUserEntityByUUID(uuidForUser);
        UserEntity userFrom = userService.getUserEntityByUUID(uuidReviewer);

        sendMail(userFor, userFrom, NotificationType.RECOMMENDATION);
        return NotificationMapper.mapToProjection(saveNotification(userFor, userFrom, NotificationType.RECOMMENDATION));
    }

    private NotificationEntity saveNotification(UserEntity userFor, UserEntity userFrom, NotificationType notificationType) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setNotificationType(notificationType);
        notificationEntity.setCreationTS(ZonedDateTime.now());
        if (Role.COMPANY.equals(userFrom.getUserType())) {
            notificationEntity.setText(notificationType.getNotificationValue(userFrom.getCompanyName()));
        } else {
            notificationEntity.setText(notificationType.getNotificationValue(userFrom.getFirstName()));
        }
        notificationEntity.setViewed(false);
        notificationEntity.setFromUser(userFrom);
        notificationEntity.setRecipientUser(userFor);
        return notificationRepository.save(notificationEntity);
    }
    
    private void sendMail(UserEntity userFor, UserEntity userFrom, NotificationType type) {
        String text;
        if (Role.COMPANY.equals(userFrom.getUserType())) {
            text = type.getNotificationValue(userFrom.getCompanyName());
        } else {
            text = type.getNotificationValue(userFrom.getFirstName());
        }
        emailService.sendEmail("New notification", text, userFor);
    }



    @Override
    public void sendTestMail() {
        emailService.sendTestEmail();
    }
}
