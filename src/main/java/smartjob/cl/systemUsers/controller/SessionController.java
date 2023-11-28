package smartjob.cl.systemUsers.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.UserSession;
import smartjob.cl.systemUsers.model.responses.UserResponse;
import smartjob.cl.systemUsers.services.SessionService;
import smartjob.cl.systemUsers.services.UserService;

@RestController
@RequestMapping("/api/")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Operation(description = "Create a new session")
    @PostMapping("session/create")
    public ResponseEntity<UserSession> createSession(@RequestBody UserSession session) {
        UserSession sessionResponse = sessionService.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionResponse);
    }

    @Operation(description = "Get a session by ID")
    @GetMapping("session/{id}")
    public ResponseEntity<UserSession> getSessionById(@PathVariable Long id) {
        UserSession sessionResponse = sessionService.findById(id);
        if (sessionResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(sessionResponse);
    }

    @Operation(description = "Update session information")
    @PutMapping("session/{id}")
    public ResponseEntity<UserSession> updateSession(@PathVariable Long id, @RequestBody UserSession session) {
        UserSession updatedSession = sessionService.update(id, session);
        if (updatedSession == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedSession);
    }

    @Operation(description = "Delete a session")
    @DeleteMapping("session/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {
        boolean isDeleted = sessionService.delete(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
