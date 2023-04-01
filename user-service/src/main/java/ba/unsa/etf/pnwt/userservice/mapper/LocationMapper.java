package ba.unsa.etf.pnwt.userservice.mapper;

import ba.unsa.etf.pnwt.userservice.dto.LocationDTO;
import ba.unsa.etf.pnwt.userservice.entity.LocationEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LocationMapper {

    public static LocationDTO mapToProjection(LocationEntity entity) {
        if (entity == null) {
            return null;
        }
        LocationDTO projection = new LocationDTO();
        projection.setId(entity.getId());
        projection.setCity(entity.getCity());
        projection.setCountry(entity.getCountry());
        projection.setDisplayValue(entity.getCity() + ", " + entity.getCountry());
        return projection;
    }

    public static List<LocationDTO> mapToProjections(List<LocationEntity> entities) {
        return entities.stream().map(LocationMapper::mapToProjection).collect(Collectors.toList());
    }
}
