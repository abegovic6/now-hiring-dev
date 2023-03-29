package ba.unsa.etf.pnwt.userservice.mapper;

import ba.unsa.etf.pnwt.userservice.dto.CountryDTO;
import ba.unsa.etf.pnwt.userservice.dto.ExampleDTO;
import ba.unsa.etf.pnwt.userservice.entity.CountryEntity;
import ba.unsa.etf.pnwt.userservice.entity.ExampleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CountryMapper {

    public static CountryDTO mapToProjection(CountryEntity entity) {
        if (entity == null) {
            return null;
        }
        CountryDTO projection = new CountryDTO();
        projection.setId(entity.getId());
        projection.setName(projection.getName());
        return projection;
    }

    public static List<CountryDTO> mapToProjections(List<CountryEntity> entities) {
        return entities.stream().map(CountryMapper::mapToProjection).collect(Collectors.toList());
    }
}
