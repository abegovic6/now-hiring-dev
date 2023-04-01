package ba.unsa.etf.pnwt.userservice.repository;

import ba.unsa.etf.pnwt.userservice.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

    LocationEntity findLocationEntityByCityAndCountry(String cityName, String countryName);

    List<LocationEntity> findLocationEntityByCityContainingIgnoreCaseOrCountryContainingIgnoreCase
            (String searchValueCity, String searchValueCountry);
}
