package smartjob.cl.systemUsers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smartjob.cl.systemUsers.entity.PhonesEntity;

@Repository
public interface PhonesRepository extends JpaRepository<PhonesEntity, Long> {
}
