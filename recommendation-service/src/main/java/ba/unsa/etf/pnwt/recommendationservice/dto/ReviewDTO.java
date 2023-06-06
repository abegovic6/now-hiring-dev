package ba.unsa.etf.pnwt.recommendationservice.dto;

import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;

public class ReviewDTO {
    private UserEntity creator;
    private UserEntity receiver;
    private String comment;
    private float number;

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
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

    public void setNumber(float number) {
        this.number = number;
    }
}
