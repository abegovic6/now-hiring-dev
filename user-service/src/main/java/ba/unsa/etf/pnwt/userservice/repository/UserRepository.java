package ba.unsa.etf.pnwt.userservice.repository;

import ba.unsa.etf.pnwt.userservice.entity.ExampleEntity;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findUserEntityByEmail(String email);

    UserEntity findUserEntityById(int id);

    UserEntity findUserEntityByUuid(String uuid);

}
