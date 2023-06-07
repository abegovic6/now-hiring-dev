package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.ApiResponseMessages;
import ba.unsa.etf.pnwt.userservice.constants.ConnectionStatus;
import ba.unsa.etf.pnwt.userservice.dto.NotificationDTO;
import ba.unsa.etf.pnwt.userservice.dto.UserDTO;
import ba.unsa.etf.pnwt.userservice.entity.ConnectionEntity;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import ba.unsa.etf.pnwt.userservice.exception.NotValidException;
import ba.unsa.etf.pnwt.userservice.mapper.UserMapper;
import ba.unsa.etf.pnwt.userservice.repository.ConnectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ConnectionServiceImpl implements ConnectionService{
    @Autowired protected UserService userService;
    @Autowired protected ConnectionRepository connectionRepository;
    @Autowired protected NotificationService notificationService;

    @Override
    public List<UserDTO> findAllConnectionsByUUID(String uuid) {
        return UserMapper.mapToProjections(findAllConnectionEntitiesByUUID(uuid, ConnectionStatus.ACCEPTED));
    }

    @Override
    public List<UserDTO> findAllConnectionRequestsByUUID(String uuid) {
        return UserMapper.mapToProjections(findAllConnectionEntitiesByUUID(uuid, ConnectionStatus.PENDING));
    }

    public List<UserEntity> findAllConnectionEntitiesByUUID(String uuid, ConnectionStatus connectionStatus) {
        UserEntity user = userService.getUserEntityByUUID(uuid);
        List<ConnectionEntity> allConnections
                = connectionRepository.getConnectionEntitiesByUserFromOrUserTo( user, user)
                .stream().filter(connectionEntity -> connectionStatus.equals(connectionEntity.getConnectionStatus()))
                .toList();

        List<UserEntity> connectedUsers = new ArrayList<>();
        for (var connection: allConnections) {
            if (connectionStatus.equals(ConnectionStatus.ACCEPTED) && !Objects.equals(connection.getUserFrom().getUuid(), uuid)) {
                connectedUsers.add(connection.getUserFrom());
            }
            if (!Objects.equals(connection.getUserTo().getUuid(), uuid)) {
                connectedUsers.add(connection.getUserTo());
            }
        }
        return connectedUsers;
    }

    @Override
    public NotificationDTO sendConnectionRequest(String uuidTo, String uuidFrom) {
        if (uuidTo.equals(uuidFrom)) {
            throw new NotValidException(ApiResponseMessages.CANT_HAVE_CONNECTION_WITH_YOURSELF);
        }
        UserEntity userTo = userService.getUserEntityByUUID(uuidTo);
        UserEntity userFrom = userService.getUserEntityByUUID(uuidFrom);

        ConnectionEntity oldConnection = connectionRepository.getConnectionEntityByUserFromAndUserToAndConnectionStatusIn(
                userFrom, userTo, List.of(ConnectionStatus.PENDING, ConnectionStatus.ACCEPTED)
        );

        if (oldConnection != null) {
            throw new NotValidException(ApiResponseMessages.CONNECTION_ALREADY_EXISTS);
        }

        ConnectionEntity connection = new ConnectionEntity(userTo, userFrom);
        connection.setConnectionStatus(ConnectionStatus.PENDING);
        connection.setCreationTS(ZonedDateTime.now());
        connection.setModificationTS(ZonedDateTime.now());
        connectionRepository.save(connection);

        return notificationService.createConnectionNotification(uuidFrom, uuidTo);
    }

    @Override
    public void acceptConnection(String uuidTo, String uuidFrom) {
        ConnectionEntity connection = findConnection(uuidTo, uuidFrom);

        if (connection == null) {
            throw new EntityNotFoundException(ApiResponseMessages.CONNECTION_FOR_ACCEPTANCE_DOES_NOT_EXISTS);
        }

        connection.setConnectionStatus(ConnectionStatus.ACCEPTED);
        connection.setModificationTS(ZonedDateTime.now());
        connectionRepository.save(connection);
    }

    @Override
    public void rejectConnection(String uuidTo, String uuidFrom) {
        ConnectionEntity connection = findConnection(uuidTo, uuidFrom);

        if (connection == null) {
            throw new EntityNotFoundException(ApiResponseMessages.CONNECTION_FOR_REJECTING_DOES_NOT_EXISTS);
        }

        connection.setConnectionStatus(ConnectionStatus.REJECTED);
        connection.setModificationTS(ZonedDateTime.now());
        connectionRepository.save(connection);
    }

    private ConnectionEntity findConnection(String uuidTo, String uuidFrom) {
        UserEntity userTo = userService.getUserEntityByUUID(uuidTo);
        UserEntity userFrom = userService.getUserEntityByUUID(uuidFrom);

        return connectionRepository.getConnectionEntityByUserFromAndUserToAndConnectionStatus(
                userFrom, userTo, ConnectionStatus.PENDING
        );
    }
}
