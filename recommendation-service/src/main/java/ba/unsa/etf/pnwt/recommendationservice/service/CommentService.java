package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.dto.CommentEntityHelper;
import ba.unsa.etf.pnwt.recommendationservice.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getAllComments();

    List<CommentEntity> getCommentByUser(Long id);

    List<CommentEntity> getCommentByUserEmail(String email);

    CommentEntity addNewComment(CommentEntityHelper comment);
}
