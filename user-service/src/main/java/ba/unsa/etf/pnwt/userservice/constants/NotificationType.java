package ba.unsa.etf.pnwt.userservice.constants;

import ba.unsa.etf.pnwt.userservice.entity.UserEntity;

public enum NotificationType {
    CONNECTION,
    JOB_CREATED,
    JOB_APPLICATION,
    RECOMMENDATION,
    REVIEW;

    public String getNotificationValue(String name) {
        switch (this) {
            case REVIEW -> {
                return name + " wrote a review about you.";
            }
            case CONNECTION -> {
                return name + " wants to connect.";
            }
            case JOB_CREATED -> {
                return name + " posted a new job application.";
            }
            case JOB_APPLICATION -> {
                return name + " just applied for a position.";
            }
            case RECOMMENDATION -> {
                return name + " thinks you will be a great applicant.";
            }
            default -> {
                return null;
            }
        }
    }
}
