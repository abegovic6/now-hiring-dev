package ba.unsa.etf.pnwt.repository;

import ba.unsa.etf.pnwt.entity.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {

}
