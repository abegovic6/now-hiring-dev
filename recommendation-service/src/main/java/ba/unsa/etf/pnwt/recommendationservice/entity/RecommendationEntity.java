package ba.unsa.etf.pnwt.recommendationservice.entity;

import ba.unsa.etf.pnwt.recommendationservice.constants.DatabaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(schema = DatabaseConstants.RECOMMENDATION_SERVICE_SCHEMA, name = "recommendation")
public class RecommendationEntity {
    @Id
    @SequenceGenerator(name = "recommendation_sequence", sequenceName = "recommendation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recommendation_sequence")
    private Long id;
//    @NotBlank(message = "Name is mandatory")
//    @NotNull(message = "Name cannot be null")
//    private String job;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "creator_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "job_id", nullable = false)
    private JobEntity jobEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "recommended_id", nullable = false)
    private UserEntity recommendedUser;

    @OneToMany(mappedBy = "recommendationEntity", cascade = CascadeType.ALL)
    private Set<CommentEntity> commentEntities;


    public RecommendationEntity() {
    }

    public RecommendationEntity( String description, UserEntity userEntity, JobEntity jobEntity, UserEntity recommendedUser) {
        this.description = description;
        this.userEntity = userEntity;
        this.jobEntity = jobEntity;
        this.recommendedUser = recommendedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public JobEntity getJobEntity() {
        return jobEntity;
    }

    public void setJobEntity(JobEntity jobEntity) {
        this.jobEntity = jobEntity;
    }

    public UserEntity getRecommendedUser() {
        return recommendedUser;
    }

    public void setRecommendedUser(UserEntity recommendedUser) {
        this.recommendedUser = recommendedUser;

    }
}
