package smartjob.cl.systemUsers.services;

import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.responses.UserResponse;

public interface UserService {

    UserResponse save(User userData);
}
