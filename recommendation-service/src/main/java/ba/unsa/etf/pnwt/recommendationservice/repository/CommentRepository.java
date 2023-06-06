package ba.unsa.etf.pnwt.recommendationservice.repository;

import ba.unsa.etf.pnwt.recommendationservice.entity.CommentEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.RecommendationEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c where c.userEntity.id=?1")
    List<CommentEntity> findCommentsByUser(Long id);

    @Query("SELECT c FROM CommentEntity c where c.userEntity.email=?1")
    List<CommentEntity> findCommentsByUserEmail(String email);

    @Query("SELECT u from UserEntity u where u.id=?1")
    Optional<UserEntity> getUserById(Long userCreatorId);
    @Query("SELECT r from RecommendationEntity r where r.id=?1")
    Optional<RecommendationEntity> getRecommendationById(Long recommendationId);
}
