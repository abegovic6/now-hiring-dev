package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.UserDTO;

import java.util.List;

public interface ConnectionService {
    List<UserDTO> findAllConnectionsByUUID(String uuid);

    void sendConnectionRequest(String uuidTo, String uuidFrom);

    void acceptConnection(String uuidTo, String uuidFrom);

    void rejectConnection(String uuidTo, String uuidFrom);
}
