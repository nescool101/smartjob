package smartjob.cl.systemUsers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import smartjob.cl.systemUsers.entity.UserEntity;
import smartjob.cl.systemUsers.model.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String mail);

}
