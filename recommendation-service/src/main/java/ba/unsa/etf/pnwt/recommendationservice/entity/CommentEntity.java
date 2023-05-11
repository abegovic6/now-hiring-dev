package ba.unsa.etf.pnwt.recommendationservice.entity;

import ba.unsa.etf.pnwt.recommendationservice.constants.DatabaseConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(schema = DatabaseConstants.RECOMMENDATION_SERVICE_SCHEMA, name="comment")
public class CommentEntity {
    @Id
    @SequenceGenerator(
            name="comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "creator_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "recommendation_id", nullable = false)
    private RecommendationEntity recommendationEntity;

    @NotNull
    private String commentContent;

    public CommentEntity() {
    }

    public CommentEntity(UserEntity userEntity, RecommendationEntity recommendationEntity, String commentContent) {
        this.userEntity = userEntity;
        this.recommendationEntity = recommendationEntity;
        this.commentContent = commentContent;
    }

    public CommentEntity(Long id, UserEntity userEntity, RecommendationEntity recommendationEntity, String commentContent) {
        this.id = id;
        this.userEntity = userEntity;
        this.recommendationEntity = recommendationEntity;
        this.commentContent = commentContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RecommendationEntity getRecommendationEntity() {
        return recommendationEntity;
    }

    public void setRecommendationEntity(RecommendationEntity recommendationEntity) {
        this.recommendationEntity = recommendationEntity;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
