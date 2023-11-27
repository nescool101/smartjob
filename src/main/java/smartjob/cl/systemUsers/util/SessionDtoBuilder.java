package smartjob.cl.systemUsers.util;

import smartjob.cl.systemUsers.entity.SessionEntity;
import smartjob.cl.systemUsers.model.UserSession;

import java.util.UUID;

public class SessionDtoBuilder {

    public SessionEntity dtoToEntity(UserSession sessionDto) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setSessionId(sessionDto.getSessionId());

        return sessionEntity;
    }
}
