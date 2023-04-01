package ba.unsa.etf.pnwt.userservice.entity;

import ba.unsa.etf.pnwt.userservice.constants.ApplicationConstants;
import ba.unsa.etf.pnwt.userservice.constants.ConnectionStatus;
import ba.unsa.etf.pnwt.userservice.constants.UserType;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(schema = ApplicationConstants.USER_SERVICE_SCHEMA, name = "connection" )
public class ConnectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_one", nullable = false)
    private UserEntity userOne;

    @ManyToOne
    @JoinColumn(name = "user_two", nullable = false)
    private UserEntity userTwo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ConnectionStatus connectionStatus;

    @Column(name = "creation_ts", nullable = false)
    private ZonedDateTime creationTS;

    @Column(name = "modification_ts")
    private ZonedDateTime modificationTS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUserOne() {
        return userOne;
    }

    public void setUserOne(UserEntity userOne) {
        this.userOne = userOne;
    }

    public UserEntity getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(UserEntity userTwo) {
        this.userTwo = userTwo;
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
