package ba.unsa.etf.pnwt.grpc.mapper;

import ba.unsa.etf.pnwt.grpc.constants.ActionResponse;
import ba.unsa.etf.pnwt.grpc.entity.LoggingEntity;
import ba.unsa.etf.pnwt.proto.LoggingRequest;

import java.time.ZonedDateTime;

public class LoggingMapper {

    private LoggingMapper() {
    }

    public static LoggingEntity mapToEntity(LoggingRequest request) {
        if (request == null) {
            return null;
        }
        LoggingEntity entity = new LoggingEntity();
        entity.setActionResponse(ActionResponse.valueOf(request.getActionResponse()));
        entity.setUrl(request.getActionUrl());
        entity.setServiceName(request.getServiceName());
        entity.setTimeStamp(ZonedDateTime.now());
        entity.setUserUUID(request.getUserUuid());
        return entity;
    }
}
