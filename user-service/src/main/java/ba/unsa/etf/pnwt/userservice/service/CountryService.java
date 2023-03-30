package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.entity.CountryEntity;

public interface CountryService {

    CountryEntity getCountryOrCreateIfMissing(String name);
}
