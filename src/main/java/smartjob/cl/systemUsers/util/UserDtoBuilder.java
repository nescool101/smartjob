package smartjob.cl.systemUsers.util;

import smartjob.cl.systemUsers.entity.UserEntity;
import smartjob.cl.systemUsers.model.User;

import java.time.LocalDateTime;

public class UserDtoBuilder {

    private User userDto;

    private UserEntity userEntity;


    PhonesDtoBuilder phonesDtoBuilder = new PhonesDtoBuilder();
    public User entityToDto(UserEntity userEntityEntry){

        return userDto = User
                .builder()
                .userId(userEntityEntry.getUserId())
                .name(userEntityEntry.getName())
                .email(userEntityEntry.getEmail())
                .password(userEntityEntry.getPassword())
                .phones(phonesDtoBuilder.listEntityToDto(userEntityEntry.getPhones()))
                .build();
    }

    public UserEntity DtoToEntity(User userDtoEntry){

        return userEntity = UserEntity
                .builder()
                .name(userDtoEntry.getName())
                .email(userDtoEntry.getEmail())
                .password(userDtoEntry.getPassword())
                .phones(phonesDtoBuilder.listDtoToEntity(userDtoEntry.getPhones()))
                .dateCreated(LocalDateTime.now())
                .lastModified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .userIsActive(Boolean.FALSE)
                .build();
    }

}
