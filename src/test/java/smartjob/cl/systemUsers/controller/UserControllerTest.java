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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import smartjob.cl.systemUsers.model.Phones;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.UserSession;
import smartjob.cl.systemUsers.model.responses.UserResponse;
import smartjob.cl.systemUsers.services.UserService;
import smartjob.cl.systemUsers.util.GenericsValidators;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private GenericsValidators genericsValidators;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    private User userDtoEntry2;

    private  Phones phones1;

    private  Phones phones2;

    private UserSession userSession;

    private  List<Phones> phonesList;

    private final static String URI = "/api/user/";

    @BeforeEach
    public void init() throws Exception {
        phones1 = Phones.builder().number("223265654").countryCode("51").cityCode("12").build();
        phones2 = Phones.builder().number("223265654").countryCode("56").cityCode("36").build();
        phonesList = List.of(phones1, phones2);
        user = User.builder().name("Nestur").email("nestur@challenge.com").password("abcdd1233").phones(phonesList).build();
        userDtoEntry2 = User.builder().userId(1L).name("Nestur").email("nestur@challenge.com").password("abcdd1233").phones(phonesList).build();
        userSession = UserSession.builder().sessionId(1L).token("anmduwqkmnDJBhs").build();
    }

    @Test
    public void testSaveUserData_createdUserSuccessful() throws Exception {
        UserResponse mockUserResponse = new UserResponse();
        mockUserResponse.setUserId(1L);
        when(genericsValidators.mailValidate(user.getEmail())).thenReturn(Boolean.TRUE);
        when(userService.save(any(User.class))).thenReturn(mockUserResponse);

        ResultActions response = mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        response.andExpect(status().isCreated());

    }

    @Test
    public void testGetUserData_returnInfo() throws Exception {
        UserResponse mockUserResponse = new UserResponse();
        mockUserResponse.setUserId(1L);
        mockUserResponse.setSession(userSession);
        when(userService.findById(1L)).thenReturn(mockUserResponse);

        ResultActions result = mockMvc.perform(get(URI+"{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());

        verify(userService, times(1)).findById(1L);
    }

    @Test
    public void testUpdateUserData_updatedUserSuccessful() throws Exception {
        UserResponse mockUserResponse = new UserResponse();
        mockUserResponse.setUserId(1L);
        when(userService.update(any(User.class))).thenReturn(mockUserResponse);

        ResultActions response = mockMvc.perform(put(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        response.andExpect(status().isOk());

    }

    @Test
    public void testDeleteUserData_deleteDataUserOk() throws Exception {
        when(userService.delete(any(Long.class))).thenReturn(HttpStatus.OK);

        ResultActions response = mockMvc.perform(delete(URI+"{userId}", 1L));

        response.andExpect(status().isOk());

    }

}