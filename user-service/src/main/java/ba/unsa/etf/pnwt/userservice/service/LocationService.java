package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.dto.LocationDTO;
import ba.unsa.etf.pnwt.userservice.entity.LocationEntity;

import java.util.List;

public interface LocationService {

    LocationEntity getLocationOrCreateIfMissing(String cityName, String countryName);

    List<LocationDTO> getAllLocations(String searchValue);
}
