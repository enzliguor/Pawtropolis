package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.Bag;

@Repository
public interface BagDAO extends JpaRepository<Bag, Long> {
}
