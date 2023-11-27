package smartjob.cl.systemUsers.services;

import smartjob.cl.systemUsers.model.UserSession;

public interface SessionService {

    UserSession save(UserSession session);
    UserSession findById(Long id);
    UserSession update(UserSession session);
    boolean delete(Long id);

}
