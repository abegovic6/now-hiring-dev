package ba.unsa.etf.pnwt.recommendationservice.controller;

import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.service.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/user")
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
    @PostMapping(path = "/addNewUser")
    public ResponseEntity<UserEntity> addNewUser(@Valid @RequestBody UserEntity user){
        userService.addNewUser(user);
        return ResponseEntity.ok(user);

    }
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long id){
        userService.deleteUser(id);
    }
    @DeleteMapping(path = "/deleteByEmail")
    public UserEntity deleteUserByEmail(@RequestParam(required = true) String email){
        UserEntity userToDelete = userService.deleteUserByEmail(email);
        return userToDelete;
    }
    @PutMapping(path = "/updateUser/{userId}")
    public void updateUser(@PathVariable("userId") Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email){
        userService.updateUser(id, name, email);
    }

}
