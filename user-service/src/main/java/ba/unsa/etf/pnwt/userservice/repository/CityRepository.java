package ba.unsa.etf.pnwt.userservice.repository;

import ba.unsa.etf.pnwt.userservice.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    CityEntity findCityEntityByNameAndCountryEntityName(String cityName, String countryName);
}
