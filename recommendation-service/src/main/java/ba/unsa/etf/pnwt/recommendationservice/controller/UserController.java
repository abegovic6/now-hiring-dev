package ba.unsa.etf.pnwt.recommendationservice.controller;

import ba.unsa.etf.pnwt.recommendationservice.dto.UserDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.exceptions.ApiRequestException;
import ba.unsa.etf.pnwt.recommendationservice.service.UserServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="recommendation-service/user")
@Validated
public class UserController {
    private final UserServiceImp userService;
    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getUsers(){
        return userService.getUser();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable("email") String email){
        UserEntity user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    @PostMapping(path = "/addNewUser")
    public ResponseEntity<UserEntity> addNewUser(@Validated @RequestBody UserEntity user, Errors errors){
        if(errors.hasErrors()){
            throw new ApiRequestException("Email must be valid!");
        }
        userService.addNewUser(user);
        return ResponseEntity.ok(user);
    }
    @PostMapping(path = "/addNewUserDTO")
    public ResponseEntity<UserEntity> addNewUserDTO(@Validated @RequestBody UserDTO user, Errors errors){
        if(errors.hasErrors()){
            throw new ApiRequestException("Email must be valid!");
        }
        UserEntity addedUser = userService.addNewUser(user);
        return ResponseEntity.ok(addedUser);
    }
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@Validated @PathVariable("userId") Long id){
        userService.deleteUser(id);
    }
    @DeleteMapping(path = "/deleteByEmail")
    public UserEntity deleteUserByEmail(@RequestParam(required = true) @Validated String email){
        UserEntity userToDelete = userService.deleteUserByEmail(email);
        return userToDelete;
    }
    @PutMapping(path = "/updateUser/{userId}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("userId") Long id,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false)  String email){
        //nece validacija
//        if(errors.hasErrors()){
//            throw new ApiRequestException("Params must be valid!");
//        }
        UserEntity userEntity = userService.updateUser(id, name, email);
        return ResponseEntity.ok(userEntity);
    }

}
