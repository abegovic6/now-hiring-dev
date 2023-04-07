package ba.unsa.etf.pnwt.jobsservice.entity;

import ba.unsa.etf.pnwt.jobsservice.constants.DatabaseConstants;
import jakarta.persistence.*;

/**
 * Example entity
 */
@Entity
@Table(schema = DatabaseConstants.JOBS_SERVICE_SCHEMA, name = "Application" )
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "useruid")
    private String userUid;

    @Column(name = "coverletter")
    private String coverLetter;

    public ApplicationEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
