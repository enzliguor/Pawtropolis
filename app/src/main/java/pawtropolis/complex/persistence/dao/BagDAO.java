package pawtropolis.complex.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.complex.persistence.entity.Bag;
@Repository
public interface BagDAO extends JpaRepository<Bag, Long> {
}
