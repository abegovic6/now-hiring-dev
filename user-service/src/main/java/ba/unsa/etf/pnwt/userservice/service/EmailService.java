package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.entity.UserEntity;

public interface EmailService {

    void sendTestEmail();

    void sendEmail(String subject, String text, UserEntity userFor);
}
