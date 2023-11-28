package smartjob.cl.systemUsers.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import smartjob.cl.systemUsers.entity.SessionEntity;
import smartjob.cl.systemUsers.model.UserSession;
import smartjob.cl.systemUsers.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionServiceImplTest {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionServiceImpl sessionService;

    private SessionEntity sessionEntity;




    private UserSession userSession;

    @BeforeEach
    public void init() throws Exception {
        userSession = UserSession.builder().sessionId(1L).token("anmduwqkmnDJBhs").build();
        sessionEntity = SessionEntity.builder().sessionId(1L).active(true).dateCreated(LocalDateTime.now()).name("nestur").build();
    }


    @Test
    public void testCreateSession_ReturnSessionResponse() {
        when(sessionRepository.save(any(SessionEntity.class))).thenReturn(sessionEntity);
        UserSession response = sessionService.save(userSession);
        Assertions.assertNotNull(response);
    }


    @Test
    public void testGetSession_ReturnSessionResponse() {
        when(sessionRepository.findById(any(Long.class))).thenReturn(Optional.of(sessionEntity));
        UserSession response = sessionService.findById(1L);
        Assertions.assertNotNull(response);
    }


    @Test
    public void testUpdateSession_SessionUpdateSuccessful() {
        when(sessionRepository.findById(any(Long.class))).thenReturn(Optional.of(sessionEntity));
        when(sessionRepository.save(any(SessionEntity.class))).thenReturn(sessionEntity);
        UserSession response = sessionService.update(1L, userSession);
        Assertions.assertNotNull(response);
    }

    @Test
    public void testDeleteSession_ReturnHttpStatus() {
        Long userId = 1L;
        when(sessionRepository.existsById(userId)).thenReturn(false);
        Boolean deleted = sessionService.delete(userId);
        assertEquals(false, deleted);
        verify(sessionRepository, times(1)).existsById(userId);
        verify(sessionRepository, never()).deleteById(userId);
    }

}
