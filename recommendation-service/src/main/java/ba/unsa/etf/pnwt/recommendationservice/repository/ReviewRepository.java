package ba.unsa.etf.pnwt.recommendationservice.repository;

import ba.unsa.etf.pnwt.recommendationservice.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    List<ReviewEntity> getAllByUserReceiverId(Long id);


}
