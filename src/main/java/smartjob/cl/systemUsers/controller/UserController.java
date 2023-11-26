package smartjob.cl.systemUsers.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(description = "Get a user by ID")
    @GetMapping("user/{id}")
    public ResponseEntity<UserResponse> getUserById(@RequestBody User user) {
        UserResponse userResponse = userService.findById(user);
        if (userResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(200).body(userResponse);
    }

    @Operation(description = "Update user information")
    @PutMapping("user/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody User user) {
        UserResponse updatedUser = userService.update(user);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(200).body(updatedUser);
    }

    @Operation(description = "Delete a user")
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<UserResponse> deleteUser(@RequestBody User user) {
        UserResponse updatedUser = userService.delete(user);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedUser);
    }


}
