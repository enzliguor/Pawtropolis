package pawtropolis.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "state",
        discriminatorType = DiscriminatorType.STRING
)
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Table(name = "door_state")
public abstract class DoorState implements EntityDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
