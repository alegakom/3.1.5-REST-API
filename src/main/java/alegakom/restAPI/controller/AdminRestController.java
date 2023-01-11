package alegakom.restAPI.controller;

import alegakom.restAPI.model.User;
import alegakom.restAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;


    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUser() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/getAdminUser")
    public ResponseEntity<User> getAdminUser() {
        User user = userService.getPrincipalUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/adminChangePassword")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody User user) {
        user.setPassword(userService.encode(user.getPassword()));
        userService.updateUser(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<> (user, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<HttpStatus> userSaveEdit(@RequestBody User user, @PathVariable("id") Long id) {
        user.setId(id);
        user.setPassword(userService.encode(user.getPassword()));
        userService.updateUser(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }


//    @PatchMapping("/adminChangeAnyPassword")
//    public ResponseEntity<HttpStatus> changeAnyPassword(@RequestBody User user) {
//        user.setPassword(userService.encode(user.getPassword()));
//        userService.updateUser(user);
//        return new ResponseEntity<> (HttpStatus.OK);
//    }



    @PostMapping("/newUser")
    public ResponseEntity<HttpStatus> saveNewUser(@RequestBody User user) {
        user.setPassword(userService.encode(user.getPassword()));
        userService.saveUser(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        User user;
        user = userService.getUserById(id);
        user.setRoles(null);
        userService.removeUser(id);
        return new ResponseEntity<> (HttpStatus.OK);
    }




}
