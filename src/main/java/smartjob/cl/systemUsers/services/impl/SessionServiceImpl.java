package smartjob.cl.systemUsers.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smartjob.cl.systemUsers.entity.SessionEntity;
import smartjob.cl.systemUsers.model.UserSession;
import smartjob.cl.systemUsers.repository.SessionRepository;
import smartjob.cl.systemUsers.services.SessionService;
import smartjob.cl.systemUsers.util.SessionDtoBuilder;
import smartjob.cl.systemUsers.util.SessionResponseDTOBuilder;

import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    @Transactional
    public UserSession save(UserSession sessionData) {
        SessionDtoBuilder sessionDtoBuilder = new SessionDtoBuilder();
        SessionResponseDTOBuilder sessionResponseDTOBuilder = new SessionResponseDTOBuilder();
        SessionEntity sessionEntity = sessionDtoBuilder.dtoToEntity(sessionData);
        SessionEntity sessionEntityOut = sessionRepository.save(sessionEntity);

        return sessionResponseDTOBuilder.entityToDto(sessionEntityOut);
    }



    public SessionEntity dtoToEntity(UserSession sessionDto) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setSessionId(sessionDto.getSessionId());

        return sessionEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public UserSession findById(Long sessionId) {
        Optional<SessionEntity> sessionEntity = sessionRepository.findById(sessionId);
        if (sessionEntity.isEmpty()) {
            return null;
        }
        SessionResponseDTOBuilder sessionResponseDTOBuilder = new SessionResponseDTOBuilder();

        return sessionResponseDTOBuilder.entityToDto(sessionEntity.get());
    }

    @Override
    @Transactional
    public UserSession update(UserSession sessionData) {
        Optional<SessionEntity> existingSession = sessionRepository.findById(sessionData.getSessionId());
        if (existingSession.isEmpty()) {
            return null;
        }
        SessionEntity session = existingSession.get();
        SessionEntity updatedSession = sessionRepository.save(session);
        SessionResponseDTOBuilder sessionResponseDTOBuilder = new SessionResponseDTOBuilder();

        return sessionResponseDTOBuilder.entityToDto(updatedSession);
    }



    @Override
    @Transactional
    public boolean delete(Long sessionId) {
        if (!sessionRepository.existsById(sessionId)) {
            return false;
        }
        sessionRepository.deleteById(sessionId);
        return true;
    }

}
