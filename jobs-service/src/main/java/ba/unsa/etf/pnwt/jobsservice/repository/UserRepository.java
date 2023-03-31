package ba.unsa.etf.pnwt.jobsservice.repository;

import ba.unsa.etf.pnwt.jobsservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM aeesjobs.User WHERE uid = :uid", nativeQuery = true)
    UserEntity findUserEntityByUid(@Param("uid") String uid);
}
