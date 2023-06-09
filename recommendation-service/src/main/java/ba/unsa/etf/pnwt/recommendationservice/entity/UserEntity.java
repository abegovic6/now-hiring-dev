package ba.unsa.etf.pnwt.recommendationservice.entity;

import ba.unsa.etf.pnwt.recommendationservice.constants.DatabaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(schema = DatabaseConstants.RECOMMENDATION_SERVICE_SCHEMA, name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;
    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "Name cannot be null")
    private String name;
    @Email(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email cannot be null")
    private String email;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<RecommendationEntity> recommendationEntities;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<JobEntity> jobEntities;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<RecommendationEntity> recommendedUsers;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Set<CommentEntity> commentEntities;

    public UserEntity() {
    }

    public UserEntity(String id, String name, String email) {
        this.uuid = id; //samo proba ne smije ovako ostat
        this.name = name;
        this.email = email;
    }

    public UserEntity(String name, String email) {
        this.uuid = "111"; ////samo proba ne smije ovako ostat
        this.name = name;
        this.email = email;
    }

    public UserEntity(String uuid, String name, String email, Set<RecommendationEntity> recommendationEntities, Set<JobEntity> jobEntities, Set<RecommendationEntity> recommendedUsers, Set<CommentEntity> commentEntities) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.recommendationEntities = recommendationEntities;
        this.jobEntities = jobEntities;
        this.recommendedUsers = recommendedUsers;
        this.commentEntities = commentEntities;
    }

    public UserEntity(Long id, String uuid, String name, String email) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
