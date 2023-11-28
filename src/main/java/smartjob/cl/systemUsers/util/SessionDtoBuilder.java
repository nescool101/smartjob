package smartjob.cl.systemUsers.util;

import smartjob.cl.systemUsers.entity.SessionEntity;
import smartjob.cl.systemUsers.model.UserSession;

import java.time.LocalDateTime;
import java.util.UUID;

public class SessionDtoBuilder {

    public SessionEntity dtoToEntity(UserSession sessionDto) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setSessionId(sessionDto.getSessionId());
        sessionEntity.setToken(sessionDto.getToken());
        sessionEntity.setModified(LocalDateTime.now());
        sessionEntity.setLastLogin(LocalDateTime.now());
        sessionEntity.setActive(sessionDto.isActive());

        return sessionEntity;
    }
}
