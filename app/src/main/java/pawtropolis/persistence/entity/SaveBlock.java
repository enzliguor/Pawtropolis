package pawtropolis.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@ToString
@EqualsAndHashCode
@Getter
@Table(name = "save_block")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class SaveBlock implements  EntityDB{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "block_name")
    String blockName;
    @OneToOne(cascade =CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_player", referencedColumnName = "id")
    private Player player;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    private Room currentRoom;
    @Column(name = "last_save", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Timestamp timestamp;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bag", referencedColumnName = "id")
    private Bag bag;
    @Column(name = "life_points")
    private int lifePoints;
}
