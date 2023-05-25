package ba.unsa.etf.pnwt.userservice.repository;

import ba.unsa.etf.pnwt.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
