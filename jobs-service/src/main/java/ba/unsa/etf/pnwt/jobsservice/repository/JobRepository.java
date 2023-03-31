package ba.unsa.etf.pnwt.jobsservice.repository;

import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

}
