package smartjob.cl.systemUsers.util;

import smartjob.cl.systemUsers.entity.UserEntity;
import smartjob.cl.systemUsers.model.responses.UserResponse;

public class UserResponseDTOBuilder {

    private UserResponse userResponse;
    public UserResponse entityToDto(UserEntity userEntity){

        return userResponse = UserResponse
                .builder()
                .userId(userEntity.getUserId())
                .created(userEntity.getDateCreated())
                .modified(userEntity.getLastModified())
                .last_login(userEntity.getLastLogin())
                .isactive(userEntity.getUserIsActive())
                .build();
    }

}
