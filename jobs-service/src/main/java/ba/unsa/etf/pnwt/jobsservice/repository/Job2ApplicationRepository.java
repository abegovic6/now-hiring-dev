package ba.unsa.etf.pnwt.jobsservice.repository;

import ba.unsa.etf.pnwt.jobsservice.entity.Job2ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Job2ApplicationRepository extends JpaRepository<Job2ApplicationEntity, Long> {

    @Query(value = "SELECT * FROM aeesjobs.job2application WHERE applicationid = :id", nativeQuery = true)
    Job2ApplicationEntity findJob2ApplicationEntityByApplicationId(@Param("id") Integer id);

}
