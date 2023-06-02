package ba.unsa.etf.pnwt.recommendationservice.dto;

import java.io.Serializable;

public class JobDTO implements Serializable {
    private Long id;
    private String name;
    private String description;






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
