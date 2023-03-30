package ba.unsa.etf.pnwt.userservice.entity;

import ba.unsa.etf.pnwt.userservice.constants.DatabaseConstants;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(schema = DatabaseConstants.USER_SERVICE_SCHEMA, name = "connection" )
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

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity cityEntity;

    @Column(name = "creationTS", nullable = false)
    private ZonedDateTime creationTS;

    @Column(name = "modificationTS")
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

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
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
