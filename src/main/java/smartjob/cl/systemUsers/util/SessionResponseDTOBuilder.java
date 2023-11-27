package smartjob.cl.systemUsers.util;

import smartjob.cl.systemUsers.entity.SessionEntity;
import smartjob.cl.systemUsers.model.UserSession;

import java.util.UUID;

public class SessionResponseDTOBuilder {
    public UserSession entityToDto(SessionEntity sessionEntity) {
        UserSession sessionResponse = new UserSession();
        sessionResponse.setSessionId(sessionEntity.getSessionId());
        sessionResponse.setActive(true);
        sessionResponse.setToken(UUID.randomUUID().toString());
        sessionResponse.setModified(sessionEntity.getLastModified());
        sessionResponse.setLastLogin(sessionEntity.getLastLogin());

        return sessionResponse;
    }

}
