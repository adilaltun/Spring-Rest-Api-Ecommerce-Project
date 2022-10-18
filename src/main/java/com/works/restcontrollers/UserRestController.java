package com.works.restcontrollers;

import com.works.entities.User;
import com.works.props.JWTLogin;
import com.works.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    final UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/auth")
    public ResponseEntity auth (@RequestBody JWTLogin jwtLogin){
        return userService.auth(jwtLogin);
    }

    @GetMapping("/list")
    public ResponseEntity list (){
        return userService.list();
    }

    @GetMapping("/listByUserId/{userId}")
    public ResponseEntity listByUserId(@PathVariable int userId){
        return userService.listByUserId(userId);
    }

}
