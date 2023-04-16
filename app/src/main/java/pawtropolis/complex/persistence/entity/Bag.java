package pawtropolis.complex.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import pawtropolis.complex.game.domain.BagBO;
import pawtropolis.complex.persistence.PersistentObject;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "bag")
@Builder
public class Bag implements PersistentObject{

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


    @Override
    public BagBO parseToBO() {
        return BagBO.builder()
                .id(this.id)
                .availableSlot(this.getAvailableSlot())
                .items(this.items.entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey().parseToBO(),
                                Map.Entry::getValue
                        )))
                .build();
    }
}
