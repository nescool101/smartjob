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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import smartjob.cl.systemUsers.model.Phones;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.responses.UserResponse;
import smartjob.cl.systemUsers.services.UserService;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    private  Phones phones1;

    private  Phones phones2;

    private  List<Phones> phonesList;

    private final static String URI = "/api/user/create";

    @BeforeEach
    public void init() throws Exception {
        phones1 = Phones.builder().number("223265654").countryCode("51").cityCode("12").build();
        phones2 = Phones.builder().number("223265654").countryCode("56").cityCode("36").build();
        phonesList = List.of(phones1, phones2);
        user = User.builder().name("Nestur").email("nestur@challenge.com").password("abcdd1233").phones(phonesList).build();
    }

    @Test
    public void testSaveUserData_createdUserSuccessful() throws Exception {
        UserResponse mockUserResponse = new UserResponse();
        mockUserResponse.setUserId(1L);
        when(userService.save(any(User.class))).thenReturn(mockUserResponse);

        ResultActions response = mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }

}