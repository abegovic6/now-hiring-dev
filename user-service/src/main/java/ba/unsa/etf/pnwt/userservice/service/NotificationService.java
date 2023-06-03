package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    void sendTestMail();

    NotificationDTO createConnectionNotification(String uuidFrom, String uuidTo);

    List<NotificationDTO> createCompanyCreatedAJobNotification(String uuidCompany);

    NotificationDTO createUserAppliedForJobApplication(String uuidUser, String companyUuid);

    NotificationDTO createReviewerWroteAReviewForUser(String uuidReviewer, String uuidForUser);

    NotificationDTO createReviewerWroteARecommendationForUser(String uuidReviewer, String uuidForUser);
}
