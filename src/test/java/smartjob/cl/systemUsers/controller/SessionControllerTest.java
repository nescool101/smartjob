package smartjob.cl.systemUsers.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import smartjob.cl.systemUsers.model.Phones;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.UserSession;
import smartjob.cl.systemUsers.services.SessionService;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SessionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    private UserSession userSession;

    private  Phones phones1;

    private  Phones phones2;



    private  List<Phones> phonesList;

    private final static String SESSION_URI = "/api/session/";

    @BeforeEach
    public void init() throws Exception {
        phones1 = Phones.builder().number("223265654").countryCode("51").cityCode("12").build();
        phones2 = Phones.builder().number("223265654").countryCode("56").cityCode("36").build();
        phonesList = List.of(phones1, phones2);
        user = User.builder().name("Nestur").email("nestur@challenge.com").password("abcdd1233").phones(phonesList).build();
        userSession = UserSession.builder().sessionId(1L).token("anmduwqkmnDJBhs").build();
    }


    @Test
    public void testCreateSession_Successful() throws Exception {
        UserSession mockSessionResponse = new UserSession();
        mockSessionResponse.setSessionId(1L);
        when(sessionService.save(any(UserSession.class))).thenReturn(mockSessionResponse);

        ResultActions response = mockMvc.perform(post(SESSION_URI + "create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSession)));

        response.andExpect(status().isCreated());
    }

    @Test
    public void testGetSession_Successful() throws Exception {
        UserSession mockSessionResponse = new UserSession();
        mockSessionResponse.setSessionId(1L);
        when(sessionService.findById(1L)).thenReturn(mockSessionResponse);

        ResultActions result = mockMvc.perform(get(SESSION_URI + "{sessionId}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        verify(sessionService, times(1)).findById(1L);
    }
    @Test
    public void testUpdateSession_Successful() throws Exception {
        UserSession mockSessionResponse = new UserSession();
        mockSessionResponse.setSessionId(1L);
        when(sessionService.update(anyLong(), any(UserSession.class))).thenReturn(mockSessionResponse);

        ResultActions response = mockMvc.perform(put(SESSION_URI + "{sessionId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSession)));

        response.andExpect(status().isOk());
    }

    @Test
    public void testDeleteSession_Successful() throws Exception {
        when(sessionService.delete(1L)).thenReturn(true);

        ResultActions response = mockMvc.perform(delete(SESSION_URI + "{sessionId}", 1L));

        response.andExpect(status().is(204));
        verify(sessionService, times(1)).delete(1L);
    }

}