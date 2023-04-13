package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.ServerConfigValue;
import ba.unsa.etf.pnwt.userservice.constants.UserType;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    protected JavaMailSender javaMailSender;
    @Autowired protected ServerConfigService serverConfigService;

    @Override
    public void sendTestEmail() {
        if (!serverConfigService.getBooleanValue(ServerConfigValue.EMAIL_SENDING_ACTIVE)) {
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nowhiringdev@gmail.com");
        message.setTo("begovicami5@gmail.com");
        message.setSubject("Test mail");
        message.setText("Test text");
        javaMailSender.send(message);
    }

    @Override
    public void sendEmail(String subject, String text, UserEntity userFor) {
        if (!serverConfigService.getBooleanValue(ServerConfigValue.EMAIL_SENDING_ACTIVE)) {
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nowhiringdev@gmail.com");
        message.setTo(userFor.getEmail());
        message.setSubject("New notification");
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
}
