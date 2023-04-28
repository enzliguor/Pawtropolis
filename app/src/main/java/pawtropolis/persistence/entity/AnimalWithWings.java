package pawtropolis.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
public abstract class AnimalWithWings extends Animal {
    @Column(name = "wingspan")
    private double wingspan;
}
