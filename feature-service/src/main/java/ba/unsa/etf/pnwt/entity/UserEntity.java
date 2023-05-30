package ba.unsa.etf.pnwt.entity;


import ba.unsa.etf.pnwt.constants.DatabaseConstants;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(schema = DatabaseConstants.FEATURE_SERVICE_SCHEMA, name = "User" )
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;

    @Column(name = "email")
    private String email;

    public UserEntity() {
    }

    public UserEntity(String uuid, String email) {
        //this.id = id;
        this.uuid = uuid;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
