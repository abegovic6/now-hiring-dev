package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;

import java.util.List;

public interface UserService{
    public List<UserEntity> getUser();

    void addNewUser(UserEntity user);

    void deleteUser(Long id);

    UserEntity updateUser(Long id, String name, String email);

    UserEntity deleteUserByEmail(String email);
}
