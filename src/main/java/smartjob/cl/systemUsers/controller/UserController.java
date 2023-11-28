package smartjob.cl.systemUsers.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.responses.UserResponse;
import smartjob.cl.systemUsers.services.UserService;
import smartjob.cl.systemUsers.util.GenericsValidators;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    private final GenericsValidators generic;

    @Operation(description = "register user in the system")
    @PostMapping()
    public ResponseEntity<?> saveUserData(@RequestBody User user){

        UserResponse userResponse = new UserResponse();

        Boolean mailValidate = generic.mailValidate(user.getEmail());

        if (!mailValidate) {

            userResponse.setMessage("mail format incorrect");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);

        }

        userResponse =  userService.save(user);
        if (userResponse!= null && userResponse.getUserId() == null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);

        }

        userResponse.setMessage("user create successful");
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @Operation(description = "Get a user by ID")
    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse userResponse = userService.findById(userId);
        if (userResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        userResponse.setMessage("user found!");
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @Operation(description = "Update user information")
    @PutMapping()
    public ResponseEntity<UserResponse> updateUser(@RequestBody User user) {
        UserResponse updatedUser = userService.update(user);
        if (updatedUser!= null && updatedUser.getUserId() == null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedUser);
        }
        updatedUser.setMessage("user update successful");
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @Operation(description = "Delete a user")
    @DeleteMapping("{userId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long userId) {
        HttpStatus deleteUser = userService.delete(userId);
        return ResponseEntity.status(deleteUser).build();
    }

}
