package pawtropolis.complex.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.complex.persistence.entity.Animal;
@Repository
public interface AnimalDAO extends JpaRepository<Animal, Long> {
}
