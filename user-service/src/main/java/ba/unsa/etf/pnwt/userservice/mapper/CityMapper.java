package ba.unsa.etf.pnwt.userservice.mapper;

import ba.unsa.etf.pnwt.userservice.dto.CityDTO;
import ba.unsa.etf.pnwt.userservice.dto.CountryDTO;
import ba.unsa.etf.pnwt.userservice.dto.ExampleDTO;
import ba.unsa.etf.pnwt.userservice.entity.CityEntity;
import ba.unsa.etf.pnwt.userservice.entity.CountryEntity;
import ba.unsa.etf.pnwt.userservice.entity.ExampleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CityMapper {

    public static CityDTO mapToProjection(CityEntity entity) {
        if (entity == null) {
            return null;
        }
        CityDTO projection = new CityDTO();
        projection.setId(entity.getId());
        projection.setName(entity.getName());
        projection.setCountry(CountryMapper.mapToProjection(entity.getCountryEntity()));
        return projection;
    }

    public static List<CityDTO> mapToProjections(List<CityEntity> entities) {
        return entities.stream().map(CityMapper::mapToProjection).collect(Collectors.toList());
    }
}
