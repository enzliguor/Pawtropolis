package pawtropolis.complex.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.complex.persistence.entity.Item;
@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {
}
