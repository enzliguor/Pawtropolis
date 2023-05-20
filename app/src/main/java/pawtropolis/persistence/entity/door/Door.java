package pawtropolis.persistence.entity.door;

import jakarta.persistence.*;
import lombok.*;
import pawtropolis.persistence.entity.EntityDB;
import pawtropolis.persistence.entity.Room;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "door")
public class Door implements EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_a")
    private Room roomA;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_b")
    private Room roomB;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_state")
    private DoorState state;
}
