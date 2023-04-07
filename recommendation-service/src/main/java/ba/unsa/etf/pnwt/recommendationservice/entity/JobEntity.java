package ba.unsa.etf.pnwt.recommendationservice.entity;

import ba.unsa.etf.pnwt.recommendationservice.constants.DatabaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(schema = DatabaseConstants.RECOMMENDATION_SERVICE_SCHEMA, name="job")
public class JobEntity {
    @Id
    @SequenceGenerator(
            name="job_sequence",
            sequenceName = "job_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_sequence")
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "Name cannot be null")
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "jobEntity")
    private Set<RecommendationEntity> recommendationEntities;

    public JobEntity() {
    }

    public JobEntity(String name, String description, UserEntity userEntity) {
        this.name = name;
        this.description = description;
        this.userEntity = userEntity;
    }

    public JobEntity(Long id, String name, String description, UserEntity userEntity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userEntity = userEntity;
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
}
