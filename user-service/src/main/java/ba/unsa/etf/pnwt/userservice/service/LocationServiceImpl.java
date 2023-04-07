package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.LocationDTO;
import ba.unsa.etf.pnwt.userservice.entity.LocationEntity;
import ba.unsa.etf.pnwt.userservice.mapper.LocationMapper;
import ba.unsa.etf.pnwt.userservice.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    protected LocationRepository locationRepository;

    @Override
    public LocationEntity getLocationOrCreateIfMissing(String cityName, String countryName) {
        LocationEntity locationEntity = locationRepository.findLocationEntityByCityAndCountry(cityName, countryName);
        if (locationEntity == null) {
            locationEntity = new LocationEntity();
            locationEntity.setCreationTS(ZonedDateTime.now());
            locationEntity.setModificationTS(ZonedDateTime.now());
            locationEntity.setCity(cityName);
            locationEntity.setCountry(countryName);
            locationEntity = locationRepository.save(locationEntity);
        }

        return locationEntity;
    }

    /**
     * Autocomplete search of all locations
     */
    @Override
    public List<LocationDTO> getAllLocations(String searchValue) {
        if (searchValue == null) {
            return LocationMapper.mapToProjections(locationRepository.findAll());
        }
        return LocationMapper.mapToProjections(locationRepository.findLocationEntityByCityContainingIgnoreCaseOrCountryContainingIgnoreCase(searchValue, searchValue));
    }
}
