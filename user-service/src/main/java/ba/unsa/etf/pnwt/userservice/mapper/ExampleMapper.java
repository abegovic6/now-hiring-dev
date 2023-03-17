package ba.unsa.etf.pnwt.userservice.mapper;

import ba.unsa.etf.pnwt.userservice.dto.ExampleDTO;
import ba.unsa.etf.pnwt.userservice.entity.ExampleEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapps example related data
 */
public class ExampleMapper {

    public static ExampleDTO mapToProjection(ExampleEntity entity) {
        ExampleDTO projection = new ExampleDTO();
        projection.setId(entity.getId());
        projection.setExample(entity.getText());
        return projection;
    }

    public static List<ExampleDTO> mapToProjections(List<ExampleEntity> entities) {
        return entities.stream().map(ExampleMapper::mapToProjection).collect(Collectors.toList());
    }
}
