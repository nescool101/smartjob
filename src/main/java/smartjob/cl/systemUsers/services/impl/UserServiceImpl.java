package smartjob.cl.systemUsers.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smartjob.cl.systemUsers.entity.UserEntity;
import smartjob.cl.systemUsers.model.User;
import smartjob.cl.systemUsers.model.responses.UserResponse;
import smartjob.cl.systemUsers.repository.UsersRepository;
import smartjob.cl.systemUsers.services.UserService;
import smartjob.cl.systemUsers.util.UserDtoBuilder;
import smartjob.cl.systemUsers.util.UserResponseDTOBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional
    public UserResponse save(User userData) {

        UserResponse userResponse = new UserResponse();
        UserDtoBuilder userDtoBuilder = new UserDtoBuilder();
        UserResponseDTOBuilder userResponseDTOBuilder = new UserResponseDTOBuilder();
        UserEntity userEntity = userDtoBuilder.DtoToEntity(userData);
        Optional<UserEntity> existMail = usersRepository.findByEmail(userData.getEmail());

        if (!existMail.isEmpty()){

            userResponse.setMessage("mail already exist");

            return userResponse;
        }

        UserEntity userEntityOut =usersRepository.save(userEntity);
        return userResponseDTOBuilder.entityToDto(userEntityOut);

    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {
        Optional<UserEntity> userEntity = usersRepository.findById(userId);
        if (userEntity.isEmpty()) {
            return null;
        }
        UserResponseDTOBuilder userResponseDTOBuilder = new UserResponseDTOBuilder();

        return userResponseDTOBuilder.entityToDto(userEntity.get());
    }

    @Override
    @Transactional
    public UserResponse update(User userData) {

        UserResponse userResponse = new UserResponse();
        UserDtoBuilder userDtoBuilder = new UserDtoBuilder();

        Optional<UserEntity> existingUser = usersRepository.findById(userData.getUserId());
        if (existingUser.isEmpty()) {

            userResponse.setMessage("user not found!");
            return userResponse;
        }
        UserEntity userEntity = userDtoBuilder.DtoToEntity(userData);
        userEntity.setUserId(existingUser.get().getUserId());
        userEntity.setLastModified(LocalDateTime.now());
        userEntity.setDateCreated(existingUser.get().getDateCreated());

        UserEntity updatedUser = usersRepository.save(userEntity);
        UserResponseDTOBuilder userResponseDTOBuilder = new UserResponseDTOBuilder();

        return userResponseDTOBuilder.entityToDto(updatedUser);
    }

    @Override
    @Transactional
    public HttpStatus delete(Long userId) {
        if (!usersRepository.existsById(userId)) {
            return HttpStatus.NO_CONTENT;
        }
        usersRepository.deleteById(userId);

        return HttpStatus.OK;
    }

}
