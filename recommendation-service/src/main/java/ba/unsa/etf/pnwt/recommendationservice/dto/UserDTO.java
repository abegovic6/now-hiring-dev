package ba.unsa.etf.pnwt.recommendationservice.dto;

import org.springframework.lang.Nullable;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private int id;
    private String example;

    @Nullable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
