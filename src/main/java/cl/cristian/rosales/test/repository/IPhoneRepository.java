package cl.cristian.rosales.test.repository;

import cl.cristian.rosales.test.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IPhoneRepository extends JpaRepository<Phone, Long> {
}
