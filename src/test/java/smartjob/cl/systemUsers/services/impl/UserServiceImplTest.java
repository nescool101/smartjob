package smartjob.cl.systemUsers.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import smartjob.cl.systemUsers.entity.UserEntity;
import smartjob.cl.systemUsers.model.Phones;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.UserSession;
import smartjob.cl.systemUsers.model.responses.UserResponse;
import smartjob.cl.systemUsers.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    private User userDtoEntry2;

    private  Phones phones1;

    private  Phones phones2;

    private UserEntity userEntity;

    private UserSession userSession;

    private UserResponse userResponse;

    private  List<Phones> phonesList;

    @BeforeEach
    public void init() throws Exception {
        phones1 = Phones.builder().number("223265654").countryCode("51").cityCode("12").build();
        phones2 = Phones.builder().number("223265654").countryCode("56").cityCode("36").build();
        phonesList = List.of(phones1, phones2);
        user = User.builder().name("Nestur").email("nestur@challenge.com").password("abcdd1233").phones(phonesList).build();
        userDtoEntry2 = User.builder().userId(1L).name("Nestur").email("nestur@challenge.com").password("abcdd1233").phones(phonesList).build();
        userSession = UserSession.builder().sessionId(1L).token("anmduwqkmnDJBhs").build();
        userEntity = UserEntity.builder().userId(1L).userIsActive(Boolean.TRUE).name("Nestur").email("nestur@companieinc.com") .build();
        userResponse = UserResponse.builder().userId(1L).email("nestur@companieinc.com").session(userSession).build();
    }


    @Test
    public void UserService_CreateUser_returnUserDto(){

        when(usersRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        UserResponse userResponseOut = userService.save(user);
        assertThat(userResponseOut).isNotNull();

    }

    @Test
    public void UserService_GetUser_returnUserDto(){

        when(usersRepository.findById(any(Long.class))).thenReturn(Optional.of(userEntity));
        UserResponse userResponseOut = userService.findById(1L);
        assertThat(userResponseOut).isNotNull();

    }


    @Test
    public void UserService_UpdateUser_userUpdateSuccessful(){

        when(usersRepository.findById(any(Long.class))).thenReturn(Optional.of(userEntity));
        when(usersRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        UserResponse userResponseOut = userService.update(userDtoEntry2);
        assertThat(userResponseOut).isNotNull();

    }

    @Test
    public void UserService_DeleteUser_returnHttpStatus(){

        Long userId = 1L;
        when(usersRepository.existsById(userId)).thenReturn(false);
        HttpStatus result = userService.delete(userId);
        assertEquals(HttpStatus.NO_CONTENT, result);
        verify(usersRepository, times(1)).existsById(userId);
        verify(usersRepository, never()).deleteById(userId);

    }

}
