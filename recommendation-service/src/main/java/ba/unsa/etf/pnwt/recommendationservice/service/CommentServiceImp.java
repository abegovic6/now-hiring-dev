package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.dto.CommentEntityHelper;
import ba.unsa.etf.pnwt.recommendationservice.entity.CommentEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.RecommendationEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.exceptions.ApiRequestException;
import ba.unsa.etf.pnwt.recommendationservice.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService{
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentEntity> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<CommentEntity> getCommentByUser(Long id) {
        return commentRepository.findCommentsByUser(id);
    }

    @Override
    public List<CommentEntity> getCommentByUserEmail(String email) {
        return commentRepository.findCommentsByUserEmail(email);
    }

    @Override
    @Transactional
    public CommentEntity addNewComment(CommentEntityHelper comment) {
        Optional<UserEntity> userCreator = commentRepository.getUserById(comment.getUserCreatorId());
        if(!userCreator.isPresent()){
            throw new ApiRequestException("User with given id " + comment.getUserCreatorId() + " doesn't exist");
        }
        Optional<RecommendationEntity> recommendationEntity = commentRepository.getRecommendationById(comment.getRecommendationId());
        if(!recommendationEntity.isPresent()){
            throw new ApiRequestException("Recommendaton with given id "+ comment.getRecommendationId()+ " doesn't exist");
        }
        CommentEntity newComment = new CommentEntity(userCreator.get(), recommendationEntity.get(), comment.getCommentContent());
        commentRepository.save(newComment);
        return newComment;
    }
}
