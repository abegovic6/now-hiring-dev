package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.entity.CityEntity;

public interface CityService {

    CityEntity getCityOrCreateIfMissing(String cityName, String countryName);
}
