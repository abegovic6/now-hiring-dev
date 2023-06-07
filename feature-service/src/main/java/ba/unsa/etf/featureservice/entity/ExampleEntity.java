package ba.unsa.etf.featureservice.entity;

import ba.unsa.etf.featureservice.constants.DatabaseConstants;
import jakarta.persistence.*;

/**
 * Example entity
 */
@Entity
@Table(schema = DatabaseConstants.FEATURE_SERVICE_SCHEMA, name = "example" )
public class ExampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    public ExampleEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
