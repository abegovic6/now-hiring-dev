package ba.unsa.etf.featureservice.repository;

import ba.unsa.etf.featureservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findById(Integer id);

    @Query("SELECT u FROM UserEntity u WHERE u.uuid = :uuid")
    UserEntity findByUuid(@Param("uuid") String uuid);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserEntity u WHERE u.uuid = :uuid")
    void deleteByUuid(@Param("uuid") String uuid);
}
