package ba.unsa.etf.featureservice.repository;

import ba.unsa.etf.featureservice.entity.ExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<ExperienceEntity, Integer> {

    @Query("SELECT e FROM ExperienceEntity e WHERE e.user.id = :id")
    List<ExperienceEntity> findByUserId(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM ExperienceEntity e WHERE e.id = :id")
    void deleteExperience(Integer id);
}
