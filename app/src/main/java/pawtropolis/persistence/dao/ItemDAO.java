package pawtropolis.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawtropolis.persistence.entity.Item;

@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {
}
