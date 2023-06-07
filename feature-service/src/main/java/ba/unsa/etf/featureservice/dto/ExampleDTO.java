package ba.unsa.etf.featureservice.dto;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Example DTO class
 */
public class ExampleDTO implements Serializable {
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
