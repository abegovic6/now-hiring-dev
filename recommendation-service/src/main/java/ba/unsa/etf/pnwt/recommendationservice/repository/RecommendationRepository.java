package ba.unsa.etf.pnwt.recommendationservice.repository;

import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.RecommendationEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RecommendationRepository extends JpaRepository<RecommendationEntity, Long> {
    @Query("SELECT u FROM UserEntity u where u.email = ?1")
    Optional<UserEntity>findUserByEmail(String email);
    @Query("SELECT j FROM JobEntity j where j.id = ?1")
    Optional<JobEntity>findJobById(Long id);
    @Query("SELECT u FROM UserEntity u where u.id = ?1")
    Optional<UserEntity>findUserById(Long id);
}
