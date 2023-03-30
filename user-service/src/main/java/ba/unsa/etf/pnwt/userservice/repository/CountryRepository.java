package ba.unsa.etf.pnwt.userservice.repository;

import ba.unsa.etf.pnwt.userservice.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    CountryEntity getCountryEntityByName(String name);
}
