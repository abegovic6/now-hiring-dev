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
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
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

    public UserEntity() {
    }

    public UserEntity(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserEntity(String name, String email) {
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
}
