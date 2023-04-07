package ba.unsa.etf.pnwt.jobsservice.repository;

import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    @Query(value = "SELECT * FROM aeesjobs.Job WHERE company_id = :company_id", nativeQuery = true)
    List<JobEntity> findJobsForCompany(@Param("company_id") String uid);

}
