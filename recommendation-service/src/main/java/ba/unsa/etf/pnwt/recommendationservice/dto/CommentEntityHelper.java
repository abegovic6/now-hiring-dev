package ba.unsa.etf.pnwt.recommendationservice.dto;

public class CommentEntityHelper {
    private Long userCreatorId;
    private Long recommendationId;
    private String commentContent;

    public CommentEntityHelper() {
    }

    public CommentEntityHelper(Long userCreatorId, Long recommendationId, String commentContent) {
        this.userCreatorId = userCreatorId;
        this.recommendationId = recommendationId;
        this.commentContent = commentContent;
    }

    public Long getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(Long userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Long recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
