package ba.unsa.etf.pnwt.userservice.mapper;

import ba.unsa.etf.pnwt.userservice.dto.NotificationDTO;
import ba.unsa.etf.pnwt.userservice.entity.NotificationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    public static NotificationDTO mapToProjection(NotificationEntity entity) {
        if (entity == null) {
            return null;
        }
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setId(entity.getId());
        notificationDTO.setText(entity.getText());
        notificationDTO.setType(entity.getNotificationType());
        notificationDTO.setFromUser(UserMapper.mapToProjection(entity.getFromUser()));
        notificationDTO.setRecipientUser(UserMapper.mapToProjection(entity.getRecipientUser()));
        return notificationDTO;
    }

    public static List<NotificationDTO> mapToProjections(List<NotificationEntity> notificationEntities) {
        return notificationEntities.stream().map(NotificationMapper::mapToProjection).collect(Collectors.toList());
    }
}
