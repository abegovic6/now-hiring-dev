package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.entity.CityEntity;
import ba.unsa.etf.pnwt.userservice.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    protected CityRepository cityRepository;

    @Autowired
    protected CountryService countryService;

    @Override
    public CityEntity getCityOrCreateIfMissing(String cityName, String countryName) {
        CityEntity cityEntity = cityRepository.findCityEntityByNameAndCountryEntityName(cityName, countryName);
        if (cityEntity == null) {
            cityEntity = new CityEntity();
            cityEntity.setCreationTS(ZonedDateTime.now());
            cityEntity.setModificationTS(ZonedDateTime.now());
            cityEntity.setName(cityName);
            cityEntity.setCountryEntity(countryService.getCountryOrCreateIfMissing(countryName));
            cityEntity = cityRepository.save(cityEntity);
        }

        return cityEntity;
    }
}
