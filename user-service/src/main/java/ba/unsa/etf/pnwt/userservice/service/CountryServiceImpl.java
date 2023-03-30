package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.entity.CountryEntity;
import ba.unsa.etf.pnwt.userservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired protected CountryRepository countryRepository;

    @Override
    public CountryEntity getCountryOrCreateIfMissing(String name) {
        CountryEntity country = countryRepository.getCountryEntityByName(name);
        if (country == null) {
            country = new CountryEntity();
            country.setCreationTS(ZonedDateTime.now());
            country.setModificationTS(ZonedDateTime.now());
            country.setName(name);
            country = countryRepository.save(country);
        }
        return country;
    }
}
