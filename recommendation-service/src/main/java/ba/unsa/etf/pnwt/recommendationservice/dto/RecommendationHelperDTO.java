package ba.unsa.etf.pnwt.recommendationservice.dto;

public class RecommendationHelperDTO {
    private String description;
    private Long userId;
    private Long jobId;
    private Long recommendedUser;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getRecommendedUser() {
        return recommendedUser;
    }

    public void setRecommendedUser(Long recommendedUser) {
        this.recommendedUser = recommendedUser;
    }
}
