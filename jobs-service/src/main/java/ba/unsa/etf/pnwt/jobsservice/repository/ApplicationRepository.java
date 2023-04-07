package ba.unsa.etf.pnwt.jobsservice.repository;

import ba.unsa.etf.pnwt.jobsservice.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

    @Query(value = "SELECT * FROM aeesjobs.Application WHERE useruid = :uid", nativeQuery = true)
    List<ApplicationEntity> findApplicationEntityByUserUid(@Param("uid") String uid);

}
