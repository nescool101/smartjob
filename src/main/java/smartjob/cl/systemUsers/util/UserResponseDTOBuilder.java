package smartjob.cl.systemUsers.util;

import smartjob.cl.systemUsers.entity.UserEntity;
import smartjob.cl.systemUsers.model.Session;
import smartjob.cl.systemUsers.model.responses.UserResponse;

import java.util.UUID;

public class UserResponseDTOBuilder {

    private UserResponse userResponse;
    public UserResponse entityToDto(UserEntity userEntity){

        Session internalSession =Session.builder()
                .sessionId(UUID.randomUUID().toString())
                .lastLogin(userEntity.getLastLogin())
                .token(UUID.randomUUID().toString())
                .modified(userEntity.getLastModified())
                .isActive(userEntity.getUserIsActive())
                .build();

        return userResponse = UserResponse
                .builder()
                .userId(userEntity.getUserId())
                .created(userEntity.getDateCreated())
                .modified(userEntity.getLastModified())
                .session(internalSession)
                .email(userEntity.getEmail())
                .build();
    }

}
