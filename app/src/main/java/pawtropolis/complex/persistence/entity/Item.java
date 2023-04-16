package pawtropolis.complex.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import pawtropolis.complex.game.domain.ItemBO;
import pawtropolis.complex.persistence.PersistentObject;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "item")
@Builder
public class Item implements PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "slots_required")
    private int slotsRequired;


    @Override
    public ItemBO parseToBO() {
        return ItemBO.builder()
                .id(this.getId())
                .name(this.getName())
                .description(this.getDescription())
                .slotsRequired(this.getSlotsRequired())
                .build();
    }
}
