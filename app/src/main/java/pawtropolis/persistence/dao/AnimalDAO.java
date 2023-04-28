package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.Animal;

@Repository
public interface AnimalDAO extends JpaRepository<Animal, Long> {
}
