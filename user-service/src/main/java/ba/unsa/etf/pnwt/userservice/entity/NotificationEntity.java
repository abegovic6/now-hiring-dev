package ba.unsa.etf.pnwt.userservice.entity;

import ba.unsa.etf.pnwt.userservice.constants.ApplicationConstants;
import ba.unsa.etf.pnwt.userservice.constants.NotificationType;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(schema = ApplicationConstants.USER_SERVICE_SCHEMA, name = "notification" )
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "from_user", nullable = false)
    private UserEntity fromUser;

    @ManyToOne
    @JoinColumn(name = "recipient_user", nullable = false)
    private UserEntity recipientUser;

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType;

    @Column(name = "creation_ts", nullable = false)
    private ZonedDateTime creationTS;

    @Column(name = "viewed")
    private boolean viewed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity from) {
        this.fromUser = from;
    }

    public UserEntity getRecipientUser() {
        return recipientUser;
    }

    public void setRecipientUser(UserEntity recipient) {
        this.recipientUser = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }


    public ZonedDateTime getCreationTS() {
        return creationTS;
    }

    public void setCreationTS(ZonedDateTime creationTS) {
        this.creationTS = creationTS;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
}
