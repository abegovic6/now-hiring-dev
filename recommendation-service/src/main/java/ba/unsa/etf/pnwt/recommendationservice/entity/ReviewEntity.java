package ba.unsa.etf.pnwt.recommendationservice.entity;

import ba.unsa.etf.pnwt.recommendationservice.constants.DatabaseConstants;
import jakarta.persistence.*;

@Entity
@Table(schema = DatabaseConstants.RECOMMENDATION_SERVICE_SCHEMA, name="review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_creator", nullable = false)
    private UserEntity userCreator;

    @ManyToOne
    @JoinColumn(name = "user_reciever", nullable = false)
    private UserEntity userReceiver;

    @Column(name = "comment")
    private String comment;

    @Column(name = "number")
    private float number;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(UserEntity userCreator) {
        this.userCreator = userCreator;
    }

    public UserEntity getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(UserEntity userReceiver) {
        this.userReceiver = userReceiver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float text) {
        this.number = text;
    }
}
