package ba.unsa.etf.pnwt.userservice.repository;

import ba.unsa.etf.pnwt.userservice.constants.UserType;
import ba.unsa.etf.pnwt.userservice.entity.ExampleEntity;
import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import ba.unsa.etf.pnwt.userservice.params.UserParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findUserEntityByEmail(String email);

    UserEntity findUserEntityByEmailAndVerifiedIsTrue(String email);

    UserEntity findUserEntityByIdAndVerifiedIsTrue(int id);

    UserEntity findUserEntityByUuidAndVerifiedIsTrue(String uuid);

    List<UserEntity> findAllByCompanyNameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseAndVerifiedIsTrue(
            String search1, String search2, String search3);
}
