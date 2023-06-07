package ba.unsa.etf.pnwt.userservice.repository;

import ba.unsa.etf.pnwt.userservice.constants.ConnectionStatus;
import ba.unsa.etf.pnwt.userservice.entity.ConnectionEntity;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Integer> {

    List<ConnectionEntity> getConnectionEntitiesByUserFromOrUserTo
            (UserEntity userFrom, UserEntity userTo);

    ConnectionEntity getConnectionEntityByUserFromAndUserToAndConnectionStatus
            (UserEntity userFrom, UserEntity userTo, ConnectionStatus status);

    ConnectionEntity getConnectionEntityByUserFromAndUserToAndConnectionStatusIn
            (UserEntity userFrom, UserEntity userTo, List<ConnectionStatus> statuses);
}
