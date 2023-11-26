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

import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(User userData) {
        Optional<UserEntity> userEntity = usersRepository.findById(userData.getUserId());
        if (userEntity.isEmpty()) {
            return null;
        }
        UserResponseDTOBuilder userResponseDTOBuilder = new UserResponseDTOBuilder();

        return userResponseDTOBuilder.entityToDto(userEntity.get());
    }

    @Override
    @Transactional
    public UserResponse update(User userData) {
        Optional<UserEntity> existingUser = usersRepository.findById(userData.getUserId());
        if (existingUser.isEmpty()) {
            return null; // O manejar de otra manera si prefieres
        }
        // Aquí puedes agregar la lógica para actualizar los campos del usuario
        existingUser.get().setName(userData.getName());
        // Repetir para otros campos necesarios
        UserEntity updatedUser = usersRepository.save(existingUser.get());
        UserResponseDTOBuilder userResponseDTOBuilder = new UserResponseDTOBuilder();

        return userResponseDTOBuilder.entityToDto(updatedUser);
    }

    @Override
    @Transactional
    public UserResponse delete(User userData) {
        if (!usersRepository.existsById(userData.getUserId())) {
            return null;
        }
        usersRepository.deleteById(userData.getUserId());
        return null;
    }

}
