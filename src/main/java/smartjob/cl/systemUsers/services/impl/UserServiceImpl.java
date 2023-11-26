package smartjob.cl.systemUsers.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smartjob.cl.systemUsers.entity.UserEntity;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.responses.UserResponse;
import smartjob.cl.systemUsers.repository.UsersRepository;
import smartjob.cl.systemUsers.services.UserService;
import smartjob.cl.systemUsers.util.UserDtoBuilder;
import smartjob.cl.systemUsers.util.UserResponseDTOBuilder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional
    public UserResponse save(User userData) {

        UserDtoBuilder userDtoBuilder = new UserDtoBuilder();
        UserResponseDTOBuilder userResponseDTOBuilder = new UserResponseDTOBuilder();
        UserEntity userEntity = userDtoBuilder.DtoToEntity(userData);
        UserEntity userEntityOut =usersRepository.save(userEntity);


        return userResponseDTOBuilder.entityToDto(userEntityOut);

    }

}
