package alegakom.restAPI.controller;

import alegakom.restAPI.model.User;
import alegakom.restAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser() {
        User user = userService.getPrincipalUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    @PatchMapping("/changePassword")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody User user) {
        user.setPassword(userService.encode(user.getPassword()));
        userService.updateUser(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}




