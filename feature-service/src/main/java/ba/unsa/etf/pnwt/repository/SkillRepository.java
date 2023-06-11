package ba.unsa.etf.pnwt.repository;

import ba.unsa.etf.pnwt.dto.SkillDTO;
import ba.unsa.etf.pnwt.entity.SkillEntity;
import ba.unsa.etf.pnwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Integer> {

    @Query("SELECT s FROM SkillEntity s WHERE s.user.id = :id")
    List<SkillEntity> findByUserId(@Param("id") Integer id);

    @Query("SELECT u FROM UserEntity u where u.email = ?1")
    Optional<UserEntity> findUserEntityByEmail(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM SkillEntity s WHERE s.id = :id")
    void deleteSkill(@Param("id") Integer id);
}
