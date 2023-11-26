package smartjob.cl.systemUsers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smartjob.cl.systemUsers.entity.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

}
