package ba.unsa.etf.featureservice.repository;

import ba.unsa.etf.featureservice.entity.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long> {

}
