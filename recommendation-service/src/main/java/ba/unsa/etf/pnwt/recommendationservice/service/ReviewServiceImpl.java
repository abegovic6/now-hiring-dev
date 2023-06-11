package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.dto.ReviewDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.ReviewEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.mapper.UserMapper;
import ba.unsa.etf.pnwt.recommendationservice.repository.ReviewRepository;
import ba.unsa.etf.pnwt.recommendationservice.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired protected ReviewRepository reviewRepository;

    @Autowired protected UserRepository userRepository;

    @Override
    public List<ReviewDTO> getAllReviews(String uuid) {
        UserEntity receiver = userRepository.getUserEntityByUuid(uuid);
        List<ReviewEntity> entities = reviewRepository.getAllByUserReceiverId(receiver.getId());
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (var entity : entities) {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setCreator(entity.getUserCreator());
            reviewDTO.setReceiver(receiver);
            reviewDTO.setComment(entity.getComment());
            reviewDTO.setNumber(entity.getNumber());
            reviewDTOS.add(reviewDTO);
        }
        return reviewDTOS;
    }

    @Override
    public ReviewDTO addReview(ReviewDTO review) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setNumber(review.getNumber());
        reviewEntity.setUserReceiver(userRepository.getUserEntityByUuid(review.getReceiver().getUuid()));
        reviewEntity.setUserCreator(userRepository.getUserEntityByUuid(review.getCreator().getUuid()));
        reviewEntity.setComment(review.getComment());
        reviewRepository.save(reviewEntity);
        return review;
    }
}
