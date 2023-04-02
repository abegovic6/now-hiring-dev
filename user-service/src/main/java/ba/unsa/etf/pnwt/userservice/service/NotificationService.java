package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.NotificationDTO;

public interface NotificationService {

    void sendTestMail();

    void createConnectionNotification(String uuidFrom, String uuidTo);

    void createCompanyCreatedAJobNotification(String uuidCompany);

    void createUserAppliedForJobApplication(String uuidUser, String companyUuid);

    void createReviewerWroteAReviewForUser(String uuidReviewer, String uuidForUser);
}
