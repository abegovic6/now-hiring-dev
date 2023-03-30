package ba.unsa.etf.pnwt.userservice.entity;

import ba.unsa.etf.pnwt.userservice.constants.DatabaseConstants;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(schema = DatabaseConstants.USER_SERVICE_SCHEMA, name = "city" )
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity countryEntity;

    @Column(name = "creationTS", nullable = false)
    private ZonedDateTime creationTS;

    @Column(name = "modificationTS")
    private ZonedDateTime modificationTS;


    public CityEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
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
