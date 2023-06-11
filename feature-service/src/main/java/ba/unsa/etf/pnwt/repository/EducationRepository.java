package ba.unsa.etf.pnwt.repository;

import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.entity.EducationEntity;
import ba.unsa.etf.pnwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface EducationRepository extends JpaRepository<EducationEntity, Integer> {

    @Query("SELECT e FROM EducationEntity e WHERE e.user.id = :id")
    List<EducationEntity> findByUserId(@Param("id")Integer id);
    @Query("SELECT u FROM UserEntity u where u.email = ?1")
    Optional<UserEntity> findUserEntityByEmail(String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM EducationEntity e WHERE e.id = :id")
    void deleteEducationById(@Param("id") Integer id);
}
