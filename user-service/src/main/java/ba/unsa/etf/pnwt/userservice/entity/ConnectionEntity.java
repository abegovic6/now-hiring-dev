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
}
