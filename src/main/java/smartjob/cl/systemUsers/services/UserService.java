package smartjob.cl.systemUsers.services;

import org.springframework.http.HttpStatus;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.responses.UserResponse;

public interface UserService {

    UserResponse save(User userData);
    UserResponse findById(Long userId);
    UserResponse update(User userData);
    HttpStatus delete(Long userId);

}
