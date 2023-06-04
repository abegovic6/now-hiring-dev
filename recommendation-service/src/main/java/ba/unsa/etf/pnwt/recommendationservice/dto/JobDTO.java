package ba.unsa.etf.pnwt.recommendationservice.dto;

import java.io.Serializable;


public class JobDTO implements Serializable {
    private Long id;
    private String name;
    private String description;

    public JobDTO() {
    }

    public JobDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

}
