package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.NotificationType;
import ba.unsa.etf.pnwt.userservice.constants.UserType;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired protected JavaMailSender javaMailSender;
    @Autowired protected UserService userService;

    @Override
    public void createConnectionNotification(String uuidFrom, String uuidTo) {

    }

    @Override
    public void createCompanyCreatedAJobNotification(String uuidCompany) {

    }

    @Override
    public void createUserAppliedForJobApplication(String uuidUser, String companyUuid) {
        UserEntity user = userService.getUserEntityByUUID(uuidUser);
        UserEntity company = userService.getUserEntityByUUID(companyUuid);

        sendMail(company, user, NotificationType.JOB_APPLICATION);
        
    }

    @Override
    public void createReviewerWroteAReviewForUser(String uuidReviewer, String uuidForUser) {

    }
    
    private void sendMail(UserEntity userFor, UserEntity userFrom, NotificationType type) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nowhiringdev@gmail.com");
        message.setTo(userFor.getEmail());
        message.setSubject("New notification");
        String text;
        if (UserType.COMPANY.equals(userFrom.getUserType())) {
            text = type.getNotificationValue(userFrom.getCompanyName());
        } else {
            text = type.getNotificationValue(userFrom.getFirstName());
        }        
        message.setText(addFooter(text, userFor));
        javaMailSender.send(message);
    }

    private String addFooter(String text, UserEntity userFor) {
        String name;
        if (UserType.COMPANY.equals(userFor.getUserType())) {
            name = userFor.getCompanyName();
        } else {
            name = userFor.getFirstName();
        }
        return "Dear " + name + ", \n\n" + text + "\n\nSincerely,\nThe team of NowHiringDev.ba";
    }

    @Override
    public void sendTestMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nowhiringdev@gmail.com");
        message.setTo("begovicami5@gmail.com");
        message.setSubject("Test mail");
        message.setText("Test text");
        javaMailSender.send(message);
    }
}
