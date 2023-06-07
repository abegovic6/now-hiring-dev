package ba.unsa.etf.featureservice.repository;

import ba.unsa.etf.featureservice.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, Integer> {

    @Query("SELECT e FROM EducationEntity e WHERE e.user.id = :id")
    List<EducationEntity> findByUserId(@Param("id")Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM EducationEntity e WHERE e.id = :id")
    void deleteEducationById(@Param("id") Integer id);
}
