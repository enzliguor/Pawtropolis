package pawtropolis.complex.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import pawtropolis.complex.game.domain.PlayerBO;
import pawtropolis.complex.persistence.PersistentObject;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "player")
@Builder
public class Player implements PersistentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @Column(name = "name")
    private String name;
    @Getter
    @Setter
    @Column(name = "life_points")
    private int lifePoints;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "id_bag", referencedColumnName = "id")
    private Bag bag;

    @Override
    public PlayerBO parseToBO() {
        return PlayerBO.builder()
                .id(this.id)
                .name(this.name)
                .lifePoints(this.lifePoints)
                .bag(bag.parseToBO())
                .build();
    }
}
