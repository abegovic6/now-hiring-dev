package ba.unsa.etf.pnwt.userservice.service;

import ba.unsa.etf.pnwt.userservice.constants.UserType;
import ba.unsa.etf.pnwt.userservice.entity.CityEntity;
import ba.unsa.etf.pnwt.userservice.entity.CountryEntity;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import ba.unsa.etf.pnwt.userservice.repository.CityRepository;
import ba.unsa.etf.pnwt.userservice.repository.CountryRepository;
import ba.unsa.etf.pnwt.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class DummyServiceImpl implements DummyService {

    @Autowired protected UserRepository userRepository;
    @Autowired protected CityRepository cityRepository;
    @Autowired protected CountryRepository countryRepository;
    @Autowired protected UUIDService uuidService;

    @Override
    public String uploadData() {
        uploadCountriesAndCities();
        uploadUsers();
        return null;
    }

    public void uploadUsers() {
        if (userRepository.findUserEntityByEmail("abegovic6@etf.unsa.ba") == null) {
            UserEntity user = new UserEntity();
            user.setUuid(uuidService.createNewUUID());
            user.setEmail("abegovic6@etf.unsa.ba");
            user.setFirstName("Amila");
            user.setLastName("Begovic");
            user.setPassword("Sifra0001");
            user.setUserType(UserType.PRIVATE);
            user.setCityEntity(cityRepository.findCityEntityByNameAndCountryEntityName("Travnik", "Bosnia and Herzegovina"));
            setDatesForUserAndSave(user);
        }

        if (userRepository.findUserEntityByEmail("eleka1@etf.unsa.ba") == null) {
            UserEntity user = new UserEntity();
            user.setUuid(uuidService.createNewUUID());
            user.setEmail("eleka1@etf.unsa.ba");
            user.setFirstName("Elma");
            user.setLastName("Polutan");
            user.setPassword("Sifra0002");
            user.setUserType(UserType.PRIVATE);
            user.setCityEntity(cityRepository.findCityEntityByNameAndCountryEntityName("Sarajevo", "Bosnia and Herzegovina"));
            setDatesForUserAndSave(user);
        }

        if (userRepository.findUserEntityByEmail("skaleta1@etf.unsa.ba") == null) {
            UserEntity user = new UserEntity();
            user.setUuid(uuidService.createNewUUID());
            user.setEmail("skaleta1@etf.unsa.ba");
            user.setFirstName("Senija");
            user.setLastName("Kaleta");
            user.setPassword("Sifra0003");
            user.setUserType(UserType.PRIVATE);
            user.setCityEntity(cityRepository.findCityEntityByNameAndCountryEntityName("Sarajevo", "Bosnia and Herzegovina"));
            setDatesForUserAndSave(user);
        }

        if (userRepository.findUserEntityByEmail("epanjeta1@etf.unsa.ba") == null) {
            UserEntity user = new UserEntity();
            user.setUuid(uuidService.createNewUUID());
            user.setEmail("epanjeta1@etf.unsa.ba");
            user.setFirstName("Eldar");
            user.setLastName("Panjeta");
            user.setPassword("Sifra0004");
            user.setUserType(UserType.PRIVATE);
            user.setCityEntity(cityRepository.findCityEntityByNameAndCountryEntityName("Sarajevo", "Bosnia and Herzegovina"));
            setDatesForUserAndSave(user);
        }

        if (userRepository.findUserEntityByEmail("begovicami5@gmail.com") == null) {
            UserEntity user = new UserEntity();
            user.setUuid(uuidService.createNewUUID());
            user.setEmail("begovicami5@gmail.com");
            user.setCompanyName("Google");
            user.setPassword("Sifra0005");
            user.setUserType(UserType.COMPANY);
            user.setCityEntity(cityRepository.findCityEntityByNameAndCountryEntityName("Los Angeles", "USA"));
            setDatesForUserAndSave(user);
        }

    }

    private void uploadCountriesAndCities() {
        if (!countryRepository.findAll().isEmpty()) {
            return;
        }

        CountryEntity bih = new CountryEntity();
        bih.setName("Bosnia and Herzegovina");
        bih = setDatesForCountryAndSave(bih);

        CityEntity sarajevo = new CityEntity();
        sarajevo.setName("Sarajevo");
        sarajevo.setCountryEntity(bih);
        setDatesForCityAndSave(sarajevo);

        CityEntity travnik = new CityEntity();
        travnik.setName("Travnik");
        travnik.setCountryEntity(bih);
        setDatesForCityAndSave(travnik);

        CountryEntity germany = new CountryEntity();
        germany.setName("USA");
        germany = setDatesForCountryAndSave(germany);

        CityEntity berlin = new CityEntity();
        berlin.setName("Los Angeles");
        berlin.setCountryEntity(germany);
        setDatesForCityAndSave(berlin);

        CityEntity munich = new CityEntity();
        munich.setName("New York");
        munich.setCountryEntity(germany);
        setDatesForCityAndSave(munich);
    }

    private CountryEntity setDatesForCountryAndSave(CountryEntity countryEntity) {
        countryEntity.setCreationTS(ZonedDateTime.now());
        countryEntity.setModificationTS(ZonedDateTime.now());
        return countryRepository.save(countryEntity);
    }

    private void setDatesForCityAndSave(CityEntity cityEntity) {
        cityEntity.setCreationTS(ZonedDateTime.now());
        cityEntity.setModificationTS(ZonedDateTime.now());
        cityRepository.save(cityEntity);
    }

    private void setDatesForUserAndSave(UserEntity user) {
        user.setCreationTS(ZonedDateTime.now());
        user.setModificationTS(ZonedDateTime.now());
        userRepository.save(user);
    }
}
