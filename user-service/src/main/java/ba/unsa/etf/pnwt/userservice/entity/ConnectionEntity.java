package ba.unsa.etf.pnwt.userservice.entity;

import ba.unsa.etf.pnwt.userservice.constants.ApplicationConstants;
import ba.unsa.etf.pnwt.userservice.constants.ConnectionStatus;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(schema = ApplicationConstants.USER_SERVICE_SCHEMA, name = "connection" )
public class ConnectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_to", nullable = false)
    private UserEntity userTo;

    @ManyToOne
    @JoinColumn(name = "user_from", nullable = false)
    private UserEntity userFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ConnectionStatus connectionStatus;

    @Column(name = "creation_ts", nullable = false)
    private ZonedDateTime creationTS;

    @Column(name = "modification_ts")
    private ZonedDateTime modificationTS;

    public ConnectionEntity() {
    }

    public ConnectionEntity(UserEntity userTo, UserEntity userFrom) {
        this.userTo = userTo;
        this.userFrom = userFrom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUserTo() {
        return userTo;
    }

    public void setUserTo(UserEntity userOne) {
        this.userTo = userOne;
    }

    public UserEntity getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(UserEntity userTwo) {
        this.userFrom = userTwo;
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public ZonedDateTime getCreationTS() {
        return creationTS;
    }

    public void setCreationTS(ZonedDateTime creationTS) {
        this.creationTS = creationTS;
    }

    public ZonedDateTime getModificationTS() {
        return modificationTS;
    }

    public void setModificationTS(ZonedDateTime modificationTS) {
        this.modificationTS = modificationTS;
    }
}
