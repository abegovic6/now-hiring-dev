package ba.unsa.etf.pnwt.recommendationservice.repository;

import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    @Query("SELECT u FROM UserEntity u where u.email = ?1")
    Optional<UserEntity> findUserByEmail(String email);
}
