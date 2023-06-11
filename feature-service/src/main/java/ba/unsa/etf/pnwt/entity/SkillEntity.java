package ba.unsa.etf.pnwt.entity;

import ba.unsa.etf.pnwt.constants.DatabaseConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(schema = DatabaseConstants.FEATURE_SERVICE_SCHEMA, name = "skill" )
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserEntity user;

    public SkillEntity() {
    }

    public SkillEntity(Integer id, String title, UserEntity user) {
        this.id = id;
        this.title = title;
        this.user = user;
    }

    public SkillEntity(String title, UserEntity user) {
        this.title = title;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
