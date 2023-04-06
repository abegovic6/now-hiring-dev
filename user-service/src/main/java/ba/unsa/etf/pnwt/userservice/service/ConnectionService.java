package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.NotificationDTO;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;

import java.util.List;

public interface ConnectionService {
    List<UserDTO> findAllConnectionsByUUID(String uuid);

    List<UserEntity> findAllConnectionEntitiesByUUID(String uuid);

    NotificationDTO sendConnectionRequest(String uuidTo, String uuidFrom);

    void acceptConnection(String uuidTo, String uuidFrom);

    void rejectConnection(String uuidTo, String uuidFrom);
}
