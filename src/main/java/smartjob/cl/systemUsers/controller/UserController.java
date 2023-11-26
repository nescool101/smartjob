package smartjob.cl.systemUsers.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.responses.UserResponse;
import smartjob.cl.systemUsers.services.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(description = "register user in the system")
    @PostMapping("user/create")
    public ResponseEntity<?> saveUserData(@RequestBody User user){
        UserResponse userResponse =  userService.save(user);
        userResponse.setMessage("user create successful");
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

}
