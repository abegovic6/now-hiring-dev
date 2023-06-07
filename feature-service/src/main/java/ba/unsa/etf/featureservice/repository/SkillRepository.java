package ba.unsa.etf.featureservice.repository;

import ba.unsa.etf.featureservice.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Integer> {

    @Query("SELECT s FROM SkillEntity s WHERE s.user.id = :id")
    List<SkillEntity> findByUserId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM SkillEntity s WHERE s.id = :id")
    void deleteSkill(@Param("id") Integer id);
}
