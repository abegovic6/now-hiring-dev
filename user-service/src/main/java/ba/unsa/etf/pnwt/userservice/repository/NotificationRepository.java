package ba.unsa.etf.pnwt.userservice.repository;

import ba.unsa.etf.pnwt.userservice.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

}
