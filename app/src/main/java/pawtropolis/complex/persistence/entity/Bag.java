package pawtropolis.complex.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "bag")
@Builder
public class Bag implements EntityDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "available_slot")
    private int availableSlot;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "items_in_bag",
            joinColumns = {@JoinColumn(name = "id_bag", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "id_item", referencedColumnName = "id")
    @Column(name = "quantity")
    private Map<Item, Integer> items;
}
