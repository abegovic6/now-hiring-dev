package ba.unsa.etf.pnwt.jobsservice.dto;

public class JobRecommendationDTO {
    private Long id;
    private String name;
    private String description;


    private String userUuid;

    public JobRecommendationDTO() {
    }

    public JobRecommendationDTO(Long id, String name, String description, String userUuid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userUuid = userUuid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
